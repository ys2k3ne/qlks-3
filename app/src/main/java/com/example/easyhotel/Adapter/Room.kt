package com.example.easyhotel.Adapter

class Room {
    var roomId: String = ""
    var roomName: String = ""
    var hotelAddress: String = ""
    var roomPrice: String = ""
    var roomRating: Float = 0f
    var roomImage: String = ""

    constructor()

    constructor(
        roomId: String,
        roomName: String,
        hotelAddress: String,
        roomPrice: String,
        roomRating: Float,
        roomImage: String
    ) {
        this.roomId = roomId
        this.roomName = roomName
        this.hotelAddress = hotelAddress
        this.roomPrice = roomPrice
        this.roomRating = roomRating
        this.roomImage = roomImage
    }
}
