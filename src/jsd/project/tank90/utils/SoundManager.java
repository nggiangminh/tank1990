package jsd.project.tank90.utils;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class SoundManager {
    private Clip clip; // Đối tượng Clip để phát nhạc nền
    private AudioInputStream audioStream; // Đối tượng AudioInputStream để đọc âm thanh
    private FloatControl volumeControl; // Đối tượng FloatControl để điều chỉnh âm lượng

    // Phát nhạc nền
    public void playBackgroundMusic() {
        try {
            // Đọc tệp âm thanh
            File musicFile = new File("src/jsd/project/tank90/resources/sounds/soundtrack.wav");
            audioStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Lấy FloatControl để điều chỉnh âm lượng
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Chơi nhạc nền lặp lại
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playMenuBackgroundMusic() {
        try {
            // Đọc tệp âm thanh
            File musicFile = new File("src/jsd/project/tank90/resources/sounds/titlescreen.wav");
            audioStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Lấy FloatControl để điều chỉnh âm lượng
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    // Dừng nhạc nền
    public void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }



    // Điều chỉnh âm lượng (giảm/ tăng âm lượng)
    public void setVolume(float volume) {
        if (volumeControl != null) {
            // Điều chỉnh âm lượng trong khoảng -80.0 đến 6.0 (đơn vị dB)
            float volumeValue = Math.max(-80.0f, Math.min(volume, 6.0f)); // Giới hạn mức âm lượng trong khoảng này
            volumeControl.setValue(volumeValue); // Cập nhật âm lượng
        }
    }


    public static void playShotSound() {
        try {
            // Đường dẫn đến file âm thanh shot.wav
            File soundFile = new File("src/jsd/project/tank90/resources/sounds/bullet_shot.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();  // Phát âm thanh
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public static void playExplosionSound() {
        try {
            // Đường dẫn đến file âm thanh shot.wav
            File soundFile = new File("src/jsd/project/tank90/resources/sounds/explosion_1.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();  // Phát âm thanh
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }


}
