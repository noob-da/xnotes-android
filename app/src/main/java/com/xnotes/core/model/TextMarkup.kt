
package com.xnotes.core.model

import com.xnotes.core.pdf.TextQuad
import java.util.UUID

/** How a markup marks its text; [id] names it in files. */
enum class MarkupType(val id: String) {
    HIGHLIGHT("highlight"),
    UNDERLINE("underline"),
    STRIKEOUT("strikeout"),
    SQUIGGLY("squiggly");

    companion object {
        fun fromId(id: String?): MarkupType? = entries.firstOrNull { it.id == id }
    }
}

/** A PDF text markup with optional image attachments. */
class TextMarkup(
    val id: String,
    val type: MarkupType,
    val color: Rgba,
    val intensity: Double,
    val quads: List<TextQuad>,
    val text: String,
    val note: String?,
    val created: Long,
    val modified: Long,

    // Images attached to this highlight.
    val imageAttachments: List<String> = emptyList(),
) {
    fun copy(
        type: MarkupType = this.type,
        color: Rgba = this.color,
        note: String? = this.note,
        modified: Long = this.modified,
        imageAttachments: List<String> = this.imageAttachments,
    ): TextMarkup = TextMarkup(
        id, type, color, intensity, quads, text, note,
        created, modified, imageAttachments
    )

    companion object {
        fun newId(): String = UUID.randomUUID().toString()
    }
}
