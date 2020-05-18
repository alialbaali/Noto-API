package com.alialbaali.noto.domain.schema

data class ResponseSchema(
  val success: Boolean,

  val error: String? = null,

  val data: Any? = null
)