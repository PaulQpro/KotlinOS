package kotos.fs

import types.*

class FSFolder(var name: string, private val parent: FSFolder?) {
    private var subFolders = mutableListOf<FSFolder>()
    private var files = mutableListOf<FSFile>()
    fun copyTo(destination: FSFolder): bool{
        return true
    }
    fun createSubFolder(name: string){
        subFolders.add(FSFolder(name,this))
    }
    fun getSubFolder(name: string): FSFolder?{
        return subFolders.firstOrNull { it.name == name }
    }
    fun createFile(name: string, type: FSFileType, content: string){
        files.add(FSFile(name,type,content,this))
    }
    fun getFile(name: string, type: FSFileType): FSFile?{
        return files.firstOrNull { it.name == name && it.type == type}
    }
    class JsonFSFolder(original: FSFolder){
        var name = original.name
        var subFolders: Array<JsonFSFolder>
        init {
            val result = mutableListOf<JsonFSFolder>()
            for (folder in original.subFolders) {
                result.add(JsonFSFolder(folder))
            }
            subFolders = result.toTypedArray()
        }
        var files: Array<FSFile.JsonFSFile>
        init {
            val result = mutableListOf<FSFile.JsonFSFile>()
            for (file in original.files) {
                result.add(FSFile.JsonFSFile(file))
            }
            files = result.toTypedArray()
        }
        fun toFS(parent: FSFolder?): FSFolder{
            val result = FSFolder(name, parent)
            result.subFolders = mutableListOf()
            for (folder: JsonFSFolder in subFolders){
                result.subFolders.add(folder.toFS(result))
            }
            for (file in files){
                result.files.add((file.toFS(result)))
            }
            result.files = mutableListOf()
            return result
        }
    }
}