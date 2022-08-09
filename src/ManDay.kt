data class ManDay(
        private val date: String,
        private val workHour: List<PairAttendance>
) {
    override fun toString(): String {
        val sb = StringBuilder()
        workHour.forEach {
            sb.append("${it} \t")
        }
        return "${date} \t $sb"
    }
}
