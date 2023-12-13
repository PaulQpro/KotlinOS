package kotos.fs

import types.*

class FSDisk(var letter: char, var name: string) {
    var root = FSFolder(name, null)
        get() = field
        private set(value) { field = value }
    class JsonFSDisk(original: FSDisk){
        var letter = original.letter
        var name = original.name
        var root = FSFolder.JsonFSFolder(original.root)
        fun toFS(): FSDisk{
            val result = FSDisk(letter,name)
            result.root = root.toFS(null)
            return result
        }
    }
}