fun main() {

    var post = Post(comments = Comments(1,2,"Commentariy", true), likes = Likes(65))
    var post1 = post.copy()
    var wall = WallService
    wall.add(post)
    wall.add(post1)
    wall.update(
        Post(
            id = 1,
            comments = Comments(2,3,"Commentation", true),
            likes = Likes(79),
            attachment = arrayOf(
                PhotoAttachment(Photo()),
                VideoAttachment(Video()),
                AudioAttachment(Audio()),
                FileAttachment(File()),
                StickerAttachment(Sticker())
            )
        )
    )
    println(wall.createComment(1, Comments(1,2,"Отлично! Делай дальше!", true)))

}

class Comments(var fromId: Int, var date: Int, var text: String, var donut: Boolean) {
    override fun toString(): String {
        return "$fromId,$date,$text,$donut"
    }
}

class Likes(var likes: Int) {
    override fun toString(): String {
        return "$likes"
    }
}

data class Post(
    var id: Int = 0,
    val ownerId: Int = 15,
    val fromId: Int = 45,
    val createdBy: String = "Инкогнито",
    val friendsOnly: Boolean = false,
    val text: String = "Нет текста",
    val comments: Comments = Comments(1,2,"Нет коментариев", false),
    val likes: Likes = Likes(0),
    val views: Int = 0,
    var origin: Post? = null,
    var attachment: Array<Attachment> = arrayOf()
)








abstract class Attachment(val type: String)

data class Photo(
    val id: Int = 0,
    val ownerId: Int = 0,
    val photo130: String = "title",
    val photo604: String = "title"
)

data class PhotoAttachment(val photo: Photo) : Attachment("photo")

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "title",
    val duration: Int = 0
)

data class VideoAttachment(val video: Video) : Attachment("video")

data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "title",
    val duration: Int = 0
)

data class AudioAttachment(val audio: Audio) : Attachment("audio")

data class File(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "file.kt",
    val size: Int = 0
)

data class FileAttachment(val file: File) : Attachment("file")

data class Sticker(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "Smile",
    val size: Int = 0
)

data class StickerAttachment(val sticker: Sticker) : Attachment("sticker")










object WallService {
    var array = emptyArray<Post>()
    var arrayComments = emptyArray<Comments>()

    private var uniqueId = 1

    fun add(post: Post): Post {
        post.id = uniqueId++
        array += post
        return array.last()
    }

    fun update(posts: Post): Boolean {
        for ((index, post) in array.withIndex()) {
            if (post.id == posts.id) {
                array[index] = posts
                return true
            }
        }
        return false
    }

    fun createComment(postId: Int, comment: Comments): Comments {
        for(post in array) {
            if (post.id == postId) {
                arrayComments += comment
                return comment
            }
        }
        throw PostNotFoundException("Такого поста нет!")
    }

    fun clear() {
        array = emptyArray<Post>()
        uniqueId = 1
    }
}