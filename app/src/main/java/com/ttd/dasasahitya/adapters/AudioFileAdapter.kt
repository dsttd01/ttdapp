package com.ttd.dasasahitya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ttd.dasasahitya.R

/**
    Model class
*/
data class AudioFile(
    val fileName: String
)
class AudioFileAdapter (private val audioFiles: List<AudioFile>) : RecyclerView.Adapter<AudioFileAdapter.AudioFileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioFileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_dasarapada, parent, false)
        return AudioFileViewHolder(view)
    }

    override fun onBindViewHolder(holder: AudioFileViewHolder, position: Int) {
        val audioFile = audioFiles[position]
        // holder.itemView.setOnClickListener(a)
    }

    override fun getItemCount(): Int {
        return audioFiles.size
    }

    /*class AudioFileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvAudioFileName: TextView = itemView.findViewById(R.id.tvAudioFileName)

        fun bind(audioFile: String) {
            tvAudioFileName.text = audioFile
        }
    }*/
    inner class AudioFileViewHolder(item: View) : RecyclerView.ViewHolder(item)
}
