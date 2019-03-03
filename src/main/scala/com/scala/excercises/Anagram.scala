package com.scala.excercises

import java.io.File

import scala.collection.parallel.immutable.ParSeq
import scala.io.Source

object Anagram extends App{

  val inputwords = List("abc","bca","def","fgr","cba")
  val file = new File("/Users/ah85je/Documents/personalworkspace/TestSBTProject/src/main/resources/wordlist.txt")
  val inputFileWords: ParSeq[String] = Source.fromFile(file)(io.Codec.ISO8859).getLines.toList.par

  def findAnagram(word: String, words:  ParSeq[String]): (String,ParSeq[String]) =
    (word -> (words.filter( w => w.toLowerCase.sorted == word.toLowerCase.sorted && w.toLowerCase != word.toLowerCase)))


  println(findAnagram("abc",inputFileWords))
}
