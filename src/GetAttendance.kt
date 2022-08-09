import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

object GetAttendance {
    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("D:\\0.Dinh Hiep\\Du lieu cham cong\\Dulieuchamcong\\01_07_2021den31_07_2022\\01_07_2021den31_07_2022 - Copy.dat")
        try {
            val br = BufferedReader(FileReader(file))
            var st = ""
            var temp = ""
            val arr = ArrayList<AttendanceModel>()
            while (br.readLine()?.let { st = it } != null) {
                val s = splitUsingTokenizer(st)
                if (s?.get(0) == "8") {
                    if (temp != s.getOrNull(1)) {
                        Log.d(temp + "\t")
                        temp = s[1]
                        preProcessing(arr)
                        //Todo: Tính giờ trong ngày return ManDay
                        val manDay = getManDay(arr)
                        Log.d(manDay.toString())
                        arr.clear()
                        println()
                    }
                    arr.add(AttendanceModel(s[1], s[2], s[4].toInt()))
                }
            }

            br.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun preProcessing(arr: ArrayList<AttendanceModel>) {
        var lastModel: AttendanceModel? = null
        arr.forEach {
            //Todo: Nếu giống type và chênh nhau <= 12 phút thì bỏ cái đằng sau
            if (lastModel?.type == it.type
                    && DateUtils.getWorkingHour(lastModel?.date + " " + lastModel?.time, it.date + " " + it.time) <= 0.2) {
                when (it.type){
                    AttendanceStatus.CHECK_IN.ordinal -> {
                        arr.remove(it)
                    }
                    AttendanceStatus.CHECK_OUT.ordinal -> {
                        lastModel?.let { model -> arr.remove(model) }
                    }
                }
                return
            }
            lastModel = it
        }
    }

    private fun getManDay(arr: List<AttendanceModel>): ManDay? {
        if (arr.isEmpty()) return null
        val pairList = ArrayList<PairAttendance>()
        var i = 0
        while (i < arr.size) {
            //Todo: Lay item
            val model = arr[i]

            //Todo: Ghep cap
            if (model.type == AttendanceStatus.CHECK_IN.ordinal) {
                if (i + 1 >= arr.size || arr[i + 1].type == AttendanceStatus.CHECK_IN.ordinal) {
                    pairList.add(PairAttendance(_in = model, _out = null))
                    i++
                    continue
                }
                //Todo: Next item
                val workHour = DateUtils.getWorkingHour(model.date + " " + model.time, arr[i + 1].date + " " + arr[i + 1].time)
                pairList.add(PairAttendance(_in = model, _out = arr[i + 1], workHour = workHour))
                i += 2
            } else if (model.type == AttendanceStatus.CHECK_OUT.ordinal) {
                pairList.add(PairAttendance(_in = null, _out = model))
                i++
            } else {
                i++
            }
        }
        return ManDay(arr[0].date, pairList)
    }

    private fun splitUsingTokenizer(s: String?): List<String>? {
        val arr = ArrayList<String>()
        val st = StringTokenizer(s)
        while (st.hasMoreTokens()) {
            arr.add(st.nextToken())
        }
        return arr
    }
}
