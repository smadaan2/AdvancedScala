package com.scala.excercises

import scala.annotation.tailrec

object TestApp3 extends App {

  val str =
    "my.song.mp3 11b\ngreatSong.flac 1000b\nnot3.txt 5b\nvideo.mp4 200b\ngame.exe 100b\nmov!e.mkv 10000b\n"

  val validFiles = Map(
    "music" -> List("mp3", "aac", "flac"),
    "image" -> List("jpg", "bmp", "gif"),
    "movie" -> List("mp4", "avi", "mkv"),
    "other" -> List("7z", "txt", "zip")
  )

    def solution(str: String): String = {

      case class FileName(name: String, ex: String, fileType: String, byte: Int)
      val fileExtenstionRegex = """(?=.*[a-z-0.9])""".r
      val fileR = """(?=.*[a-z-0.-9])""".r
      val lines = str.split("\n")

      if (lines.length <= 500) {
        val parseR = lines.flatMap { file =>
          if (file.length <= 30) {
            file.split(" ").toList match {
              case h :: t :: Nil =>
                val fP = h.split('.')
                val fExten = fP.last
                if (fileExtenstionRegex.findAllMatchIn(fExten).nonEmpty) {
                  validFiles.find(_._2.contains(fExten)) match {
                    case Some(fileType) =>
                      Some(
                        FileName(
                          name = h,
                          ex = fP.last,
                          fileType = fileType._1,
                          byte = t.split("b").head.toInt
                        )
                      )
                    case None => None
                  }

                } else None

              case _ => None
            }
          } else None
        }.toList

        val result = parseR
          .groupBy(_.fileType)
          .map { f =>
            val total = f._2.foldLeft(0) { case (a, r) => a + r.byte }
            s"${f._1} ${total}b"
          }
          .toList.mkString("\n")

        result
      }
      else ""
    }

  println(solution(str))
}
