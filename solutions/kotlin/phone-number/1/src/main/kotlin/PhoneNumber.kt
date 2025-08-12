class PhoneNumber(raw: String) {
    val number: String

    init {
        // Xóa mọi ký tự không phải số
        var digits = raw.filter { it.isDigit() }

        // Nếu bắt đầu bằng '1' và độ dài 11 -> bỏ số '1' đầu
        if (digits.length == 11 && digits.startsWith("1")) {
            digits = digits.drop(1)
        }

        // Kiểm tra hợp lệ: phải đúng 10 chữ số
        require(digits.length == 10) { "Invalid phone number: must contain 10 digits" }

        // Theo NANP: NXX NXX-XXXX (N: 2-9, X: 0-9)
        require(digits[0] in '2'..'9') { "Invalid area code" }
        require(digits[3] in '2'..'9') { "Invalid exchange code" }

        number = digits
    }
}
