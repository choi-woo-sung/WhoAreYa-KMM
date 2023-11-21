package goal.hyunwoo.whoareya

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform