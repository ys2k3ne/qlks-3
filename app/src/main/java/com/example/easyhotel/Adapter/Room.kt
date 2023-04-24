package com.example.easyhotel.Adapter

class Room {
    var roomId: String = ""
    var roomName: String = ""
    var hotelAddress: String = ""
    var roomPrice: String = ""
    var roomRating: Float = 0f
    var roomImage: String = ""
    var description: String = ""
    var available: Boolean = false

    constructor()

    constructor(
        roomId: String,
        roomName: String,
        hotelAddress: String,
        roomPrice: String,
        roomRating: Float,
        roomImage: String,
        description: String,
        available: Boolean
    ) {
        this.roomId = roomId
        this.roomName = roomName
        this.hotelAddress = hotelAddress
        this.roomPrice = roomPrice
        this.roomRating = roomRating
        this.roomImage = roomImage
        this.description = description
        this.available = available
    }
}
