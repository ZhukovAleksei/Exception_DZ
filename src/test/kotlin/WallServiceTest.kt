import org.junit.Assert.*
import org.junit.Test
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun idNoZerro() {
        val service = WallService
        service.add(Post())
        assertEquals(1, service.array[0].id)
    }

    @Test
    fun updateTrue() {
        val service = WallService
        service.add(Post())
        var result = service.update(Post(id = 1, likes = Likes(5)))
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        val service = WallService
        service.add(Post())
        val result = service.update(Post(likes = Likes(5)))
        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val service = WallService
        service.add(Post())
        service.createComment(10,Comments(1,2,"Делай дальше!", true))
    }

    @Test
    fun createCommentNotError() {
        val service = WallService
        service.add(Post())
        val finalResult = Comments(1,2,"Делай дальше!", true)
        val result = service.createComment(1, Comments(1, 2, "Делай дальше!", true))
        assertEquals(finalResult.fromId, result.fromId)
        assertEquals(finalResult.date, result.date)
        assertEquals(finalResult.text, result.text)
        assertEquals(finalResult.donut,result.donut)
    }
}