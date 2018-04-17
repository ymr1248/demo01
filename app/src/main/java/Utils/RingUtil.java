package Utils;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;

import base.HBaseApplication;

public class RingUtil {
	/**
	 * 播放设备默认提示音
	 *
	 */
	public static void playDefaultSound() {
		try {
			MediaPlayer player = new MediaPlayer();
			Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			player.reset();
			player.setDataSource(HBaseApplication.context,uri);
			player.prepare();
			player.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 播放本地资源
	 *
	 * @param rawId
	 */
	public static void playLocalSound(int rawId) {
		try {
			MediaPlayer player = MediaPlayer.create(HBaseApplication.context, rawId);
			player.start();
			player.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
