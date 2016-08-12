/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.AdvancedMedia;

import Media.AdvancedMedia.Audio.AudioDriver;
import Media.AdvancedMedia.Video.VideoNode;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author James
 */
public class MediaPipeline {
    private Connector videoSource;
    private Connector audioSource;
    private AudioDriver audio;
    private VideoNode video;
    private final ObservableList<Codec> audioCodecs;
    private final ObservableList<Codec> videoCodecs;

    public MediaPipeline() {
        audioCodecs = javafx.collections.FXCollections.observableArrayList();
        videoCodecs = javafx.collections.FXCollections.observableArrayList();
    }
    
    public void process(){
        FXCollections.sort(audioCodecs, Comparator.comparing(Codec::getOrder));
        FXCollections.sort(videoCodecs, Comparator.comparing(Codec::getOrder));
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
    }
    
    public void removeAudioCodec(Codec c){
        audioCodecs.remove(c);
    }
    
    public void addVideoCodec(Codec c){
        videoCodecs.add(c);
    }
    
    public void removeVideoCodec(Codec c){
        videoCodecs.remove(c);
    }
}
