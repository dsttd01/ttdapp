/**
 *  Create an adapter for the ListView
 * */
package com.ttd.dasasahitya.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ttd.dasasahitya.R

class AudioFileAdapter (private val audioFiles: List<String>) : RecyclerView.Adapter<AudioFileAdapter.AudioFileViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioFileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_display, parent, false)
        return AudioFileViewHolder(view)
    }

    override fun onBindViewHolder(holder: AudioFileViewHolder, position: Int) {
        val audioFile = audioFiles[position]
        Log.d("onBindViewHolder", audioFile)
        holder.bind(audioFile)
    }

    override fun getItemCount(): Int {
        return audioFiles.size
    }

    class AudioFileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvAudioFileName: TextView = itemView.findViewById(R.id.songNameTextView)

        fun bind(audioFile: String) {
            tvAudioFileName.text = audioFile
        }
    }
}
