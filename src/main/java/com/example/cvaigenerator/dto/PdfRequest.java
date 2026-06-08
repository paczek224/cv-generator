package com.example.cvaigenerator.dto;

import lombok.Data;

/**
 * Payload for server-side PDF rendering. The client sends the already-rendered
 * CV markup (the {@code #cvDoc} element, with its inline theme variables and
 * font), so headless Chrome reproduces the on-screen preview exactly.
 */
@Data
public class PdfRequest {

    /** outerHTML of the rendered {@code .cv-doc} element. */
    private String html;

    /** Page height in millimetres, computed client-side to fit on a single page. */
    private Double heightMm;

    /** Selected font family (informational; the family is also inlined in {@code html}). */
    private String font;
}
