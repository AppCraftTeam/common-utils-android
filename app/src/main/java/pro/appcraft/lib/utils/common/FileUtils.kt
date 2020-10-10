package pro.appcraft.lib.utils.common

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import java.io.File

fun Context.getVideoFileDuration(file: File): Long? = try {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(this, Uri.fromFile(file))
    val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
    retriever.release()
    time?.toLongOrNull()
} catch (ignored: Exception) {
    null
}