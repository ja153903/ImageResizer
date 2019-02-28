import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.lang.Exception
import javax.imageio.ImageIO

@Throws(IOException::class)
fun BufferedImage.resizeToSquare(newDim: Int): BufferedImage {
    val tmp = this.getScaledInstance(newDim, newDim, Image.SCALE_DEFAULT)
    val scaledImage = BufferedImage(newDim, newDim, this.type)

    val graphics = scaledImage.createGraphics()
    graphics.drawImage(tmp, 0, 0, null)
    graphics.dispose()

    return scaledImage
}

@Throws(IOException::class)
fun BufferedImage.writeImageToPath(formatName: String, outputPath: String) {
    val outputFile = File(outputPath)
    if (outputFile.createNewFile()) {
        println("File was created")
    } else {
        println("File already exists. So we will overwrite it")
    }

    ImageIO.write(this, formatName, outputFile)
}

@Throws(Exception::class)
fun main() {
    println("Enter absolute path for image: ")
    val absolutePath = readLine()
    val input = File(absolutePath)
    if (!input.exists()) throw Exception("File doesn't exist")

    val image = ImageIO.read(input)
    println("Image type: ${image.type}")

    println("Enter new width and height: ")
    val newDimension = readLine()!!.toInt()

    val scaledImage = image.resizeToSquare(newDimension)
    val outputPath = "${input.absolutePath
        .substring(0, input.absolutePath.lastIndexOf('.'))}_result.${input.extension}"
    scaledImage.writeImageToPath(input.extension, outputPath)
}