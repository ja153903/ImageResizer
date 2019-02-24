import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.lang.Exception
import javax.imageio.ImageIO

@Throws(IOException::class)
fun BufferedImage.resizeToSquare(newHeight: Int, newWidth: Int): BufferedImage {
    val tmp = this.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT)
    val scaledImage = BufferedImage(newWidth, newHeight, this.type)

    val graphics = scaledImage.createGraphics()
    graphics.drawImage(tmp, 0, 0, null)
    graphics.dispose()

    return scaledImage
}

@Throws(Exception::class)
fun main() {
    val input = File("/Users/jaime/Downloads/beauty-bloom-blue-67636.jpg")
    val image = ImageIO.read(input)
    println("Image type: ${image.type}")

    println("Enter new width and height: ")
    val newDimension = readLine()!!.toInt()

    val scaledImage = image.resizeToSquare(newDimension, newDimension)

    val output = File("/Users/jaime/Downloads/flowerOutput.jpg")
    if (output.createNewFile()) {
        println("File created")
    } else {
        println("File already exists")
    }
    ImageIO.write(scaledImage, "jpg", output)
}