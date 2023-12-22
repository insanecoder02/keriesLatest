package com.example.keries.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import com.example.keries.R
import com.example.keries.dataClass.reelDataClass

class reelinfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reelinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val reelNameTextView = view.findViewById<TextView>(R.id.reelNameTextview)
        val videoView  = view.findViewById<VideoView>(R.id.videoView)
        val reel_Name = arguments?.getString("reelName")
        val reelId = arguments?.getString("reelID")
        val reelURl = arguments?.getString("reelURL")

        reelNameTextView.text = reel_Name

        val videoUri = Uri.parse(reelURl)
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoUri)
        videoView.requestFocus()
        videoView.start()
    }
}