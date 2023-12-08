package cn.shef.msc5.todo.utilities

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 23:24
 */
class DistanceUtil {
    companion object {
        fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
            val theta = lon1 - lon2
            var dist = (Math.sin(deg2rad(lat1))
                    * Math.sin(deg2rad(lat2))
                    + (Math.cos(deg2rad(lat1))
                    * Math.cos(deg2rad(lat2))
                    * Math.cos(deg2rad(theta))))
            dist = Math.acos(dist)
            dist = rad2deg(dist)
            dist = dist * 60 * 1.1515
            return dist
        }

        fun rad2deg(rad: Double): Double {
            return rad * 180.0 / Math.PI
        }

        fun deg2rad(deg: Double): Double {
            return deg * Math.PI / 180.0
        }

    }
}