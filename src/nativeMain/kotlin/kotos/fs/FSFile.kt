package kotos.fs

import types.*

class FSFile(var name: string, val type: FSFileType, var content: string, private val parent: FSFolder) {
    fun copy(destination: FSFolder): bool{
        return true
    }
    class JsonFSFile(original: FSFile) {
        var name = original.name
        var type = original.type
        var content = original.content
        fun toFS(parent: FSFolder): FSFile{
            return FSFile(name, type, content, parent)
        }
    }
}