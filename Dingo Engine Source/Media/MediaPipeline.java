/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media;

import Media.AdvancedMedia.Audio.AudioDriver;
import Media.AdvancedMedia.Video.VideoNode;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author James
 */
public class MediaPipeline extends Thread{
    private Connector videoSource;
    private Connector audioSource;
    private AudioDriver audio;
    private VideoNode video;
    private final ObservableList<Codec> audioCodecs;
    private final ObservableList<Codec> videoCodecs;
    private volatile boolean running;
    private int req;

    public MediaPipeline() {
        audioCodecs = javafx.collections.FXCollections.observableArrayList();
        videoCodecs = javafx.collections.FXCollections.observableArrayList();
    }
    
    @Override
    public void run(){
        System.out.println("MME Started");
        running = true;
        while (running) {
            if (req > 0) {
                req --;
                audioCodecs.stream().forEach((c) -> {
                    c.process();
                });
                videoCodecs.stream().forEach((c) -> {
                    c.process();
                });
                if (audio != null) {
                    audio.update();
                }
                if (video != null) {
                    video.update();
                }
            }
        }
        System.out.println("MME Stopped");
    }
    
    public synchronized void process(){
        req ++;
    }

    public Connector getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(Connector videoSource) {
        this.videoSource = videoSource;
    }

    public Connector getAudioSource() {
        return audioSource;
    }

    public void setAudioSource(Connector audioSource) {
        this.audioSource = audioSource;
    }

    public AudioDriver getAudio() {
        return audio;
    }

    public void setAudio(AudioDriver audio) {
        this.audio = audio;
    }

    public VideoNode getVideo() {
        return video;
    }

    public void setVideo(VideoNode video) {
        this.video = video;
    }
    
    public void addAudioCodec(Codec c){
        audioCodecs.add(c);
        sort();
    }
    
    public void removeAudioCodec(Codec c){
        audioCodecs.remove(c);
        sort();
    }
    
    public void addVideoCodec(Codec c){
        videoCodecs.add(c);
        sort();
    }
    
    public void removeVideoCodec(Codec c){
        videoCodecs.remove(c);
        sort();
    }
    
    private void sort(){
        FXCollections.sort(audioCodecs, Comparator.comparing(Codec::getOrder));
        FXCollections.sort(videoCodecs, Comparator.comparing(Codec::getOrder));
    }
    
    public void halt(){
        if(audio != null){
            audio.stop();
        }
        if(video != null){
            video.stop();
        }
        running = false;
    }
}
