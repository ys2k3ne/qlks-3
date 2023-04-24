package com.example.easyhotel

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.easyhotel.Adapter.Booking
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class BookingDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_ROOM_ID = "roomId"
        private const val ARG_ROOM_NAME = "roomName"
        private const val ARG_CHECK_IN_DATE = "checkInDate"
        private const val ARG_CHECK_OUT_DATE = "checkOutDate"

        fun newInstance(roomId: String, roomName: String, checkInDate: Long, checkOutDate: Long): BookingDialogFragment {
            return BookingDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ROOM_ID, roomId)
                    putString(ARG_ROOM_NAME, roomName)
                    putLong(ARG_CHECK_IN_DATE, checkInDate)
                    putLong(ARG_CHECK_OUT_DATE, checkOutDate)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val roomId = arguments?.getString(ARG_ROOM_ID)
        val roomName = arguments?.getString(ARG_ROOM_NAME)
        val checkInDate = arguments?.getLong(ARG_CHECK_IN_DATE)
        val checkOutDate = arguments?.getLong(ARG_CHECK_OUT_DATE)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Đặt phòng $roomName")
            .setMessage("Bạn có muốn đặt phòng này không?")
            .setPositiveButton("Đặt phòng") { _, _ ->
                val user = FirebaseAuth.getInstance().currentUser
                if (user == null) {
                    Toast.makeText(requireContext(), "Bạn cần đăng nhập để đặt phòng", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val db = FirebaseFirestore.getInstance()
                val bookingId = UUID.randomUUID().toString()
                val checkInDateObj = Date(checkInDate!!)
                val checkOutDateObj = Date(checkOutDate!!)
                val booking = Booking(bookingId, roomId!!, roomName!!, checkInDateObj, checkOutDateObj)

                db.collection("bookings")
                    .document(bookingId)
                    .set(booking)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Đặt phòng $roomName thành công!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Đặt phòng $roomName thất bại: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            .setNegativeButton("Hủy") { _, _ ->
                Toast.makeText(requireContext(), "Bạn đã hủy đặt phòng $roomName", Toast.LENGTH_SHORT).show()
            }
            .create()
        dialog.show()
        return dialog
    }
}
