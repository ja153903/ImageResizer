import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

@Throws(Exception::class)
fun main() {
    val input = File("/Users/jaime/Downloads/beauty-bloom-blue-67636.jpg")
    val image = ImageIO.read(input)
    println("Image type: ${image.type}")

    val newHeight = 500
    val newWidth = 500

    val tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT)
    val type = image?.type ?: BufferedImage.TYPE_INT_ARGB
    val scaledImage = BufferedImage(newWidth, newHeight, type)

    val graphics = scaledImage.createGraphics()
    graphics.drawImage(tmp, 0, 0, null)
    graphics.dispose()

    val output = File("/Users/jaime/Downloads/flowerOutput.jpg")
    if (output.createNewFile()) {
        println("File created")
    } else {
        println("File already exists")
    }
    ImageIO.write(scaledImage, "jpg", output)
}