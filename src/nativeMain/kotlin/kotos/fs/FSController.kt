package kotos.fs

import types.*

class FSController {
    var disks = mutableListOf<FSDisk>()
    fun plugDisk(letter: char, name: string){
        disks.add(FSDisk(letter, name))
    }
    fun getDisk(letter: char): FSDisk?{
        return disks.firstOrNull { it.letter == letter }
    }
    class JsonFSController(original: FSController){
        var disks: Array<FSDisk.JsonFSDisk>
        init {
            val result = mutableListOf<FSDisk.JsonFSDisk>()
            for(disk in original.disks){
                result.add(FSDisk.JsonFSDisk(disk))
            }
            disks = result.toTypedArray()
        }
        fun toFS(): FSController{
            val result = FSController()
            for (disk in disks){
                result.disks.add(disk.toFS())
            }
            return result
        }
    }
}