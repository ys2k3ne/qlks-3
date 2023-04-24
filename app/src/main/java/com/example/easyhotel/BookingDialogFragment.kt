package com.example.easyhotel

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class BookingDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(roomId: String, roomName: String): BookingDialogFragment {
            val args = Bundle()
            args.putString("room_id", roomId)
            args.putString("room_name", roomName)

            val fragment = BookingDialogFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val roomId = arguments?.getString("room_id")
        val roomName = arguments?.getString("room_name")

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Đặt phòng $roomName")
            .setMessage("Bạn có muốn đặt phòng này không?")
            .setPositiveButton("Đặt phòng") { _, _ ->
                Toast.makeText(requireContext(), "Bạn đã đặt phòng $roomName", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Hủy") { _, _ ->
                Toast.makeText(requireContext(), "Bạn đã hủy đặt phòng $roomName", Toast.LENGTH_SHORT).show()
            }
            .create()

        return dialog
    }
}
