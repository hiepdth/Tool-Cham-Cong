data class AttendanceModel(val date: String,
                           val time: String?,
                           val type: Int) {

    override fun toString(): String {
        return time + "\t" + type + "\t"
    }
}
