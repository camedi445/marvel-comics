package co.cmedina.marvelcomics.data.api

import java.security.MessageDigest


fun generateHash() : Pair<String, String> {
    val ts = getCurrentTimestampAsString()
    val hash = calculateMD5Hash("$ts$PRIVATE_KEY$PUBLIC_KEY")
    return Pair(ts,hash)
}

private fun calculateMD5Hash(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    md.update(input.toByteArray())
    val bytes = md.digest()
    val stringBuilder = StringBuilder()
    for (byte in bytes) {
        stringBuilder.append(String.format("%02x", byte))
    }

    return stringBuilder.toString()
}

private fun getCurrentTimestampAsString(): String {
   return System.currentTimeMillis().toString()
}


const val PUBLIC_KEY = "caae9e9ac7eb9d5ea17c85250dba1abc"
private const val PRIVATE_KEY = "46efedddd56ba3375138cb145d0d9cedd52043ec"