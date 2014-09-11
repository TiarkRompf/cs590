package cs590.week2

trait Images {

  type Image = Point => Color
  type Point = (DoubleE, DoubleE)
  type Color = (DoubleE, DoubleE, DoubleE)

  abstract class DoubleE
  case class Const(d: Double) extends DoubleE
  case class Sym(x: String) extends DoubleE

  implicit def unit(d: Double) = Const(d)

}

trait ImagesPoly {

  // see section 5 in Elliott's paper

  type Image = Point => Color
  type Point = (DoubleE, DoubleE)
  type Color = (DoubleE, DoubleE, DoubleE)

  
  type DoubleE = Exp[Double]

  abstract class Exp[T]

  case class Const[T](d: T) extends Exp[T]
  case class Sym[T](x: String) extends Exp[T]

  implicit def unit[T](d: T) = Const(d)

}



trait Codegen extends Images {

  def writeFile(name: String, content: String) {
    val out = new java.io.PrintWriter(new java.io.File(name))
    out.write(content)
    out.close()
  }

  def generateImage(fileName: String, image: Image) = writeFile(fileName,template(fileName,image))

  def eval(e: DoubleE): String = e match {
    case Sym(x) => x
    case Const(d) => d.toString
  }

  def template(fileName: String, image: Image) = s"""
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
    <html>
    <head>
      <title>CS590: $fileName</title>
      <style>
      canvas { cursor: crosshair; }
      </style>
      <script>
        function drawImage() {
          var w = 300;
          var h = 300;
          var gCanvas = document.getElementById("canvas");

          var gCtx = gCanvas.getContext("2d");
          gCtx.fillRect(0,0,w,h);

          var imageData = gCtx.getImageData(0,0,w,h);

          for (var x = 0; x < imageData.width; x++) {
            for (var y = 0; y < imageData.height; y++) {
              var offset = (y * imageData.width + x) * 4;

              var r = 0;
              var g = 0;
              var b = 0;

              ${
                val (r,g,b) = image((Sym("x"),Sym("y")))
                val (rs,gs,bs) = (eval(r), eval(g), eval(b))
                s"r = $rs*255; g = $gs*255; b = $bs*255"
              }

              imageData.data[offset] = r;
              imageData.data[offset + 1] = g;
              imageData.data[offset + 2] = b;
            }
          }
          gCtx.putImageData(imageData, 0, 0);
        }
      </script>
    </head>
    <body onload="drawImage()">
      <h1>Image: $fileName</h1>
      <div id="container" align="center">
        <canvas id="canvas" width="300" height="300" style="border: 1px solid black;"/>
      </div>
    </body>
    </html>"""


}