package com.reactnativecommunity.webview

import android.app.Application
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.gigya.android.sdk.Gigya
import com.gigya.android.sdk.GigyaPluginCallback
import com.gigya.android.sdk.account.models.GigyaAccount
import com.gigya.android.sdk.session.SessionInfo
import com.gigya.android.sdk.ui.plugin.IGigyaWebBridge

class RNCGigya(context: Application) {
  private lateinit var gigya: Gigya<GigyaAccount>

  init {
    Gigya.setApplication(context)
  }

  fun initialize(apiKey: String, apiDomain: String, webview: WebView) {
    gigya = Gigya.getInstance(GigyaAccount::class.java)
    gigya.init(apiKey, apiDomain)
    attachBridge(webview)
  }

  fun login(sessionToken: String, sessionSecret: String) {
    val session = SessionInfo(sessionSecret, sessionToken)
    gigya.setSession(session)
  }

  private fun attachBridge(webview: WebView) {
    var webBridge: IGigyaWebBridge<GigyaAccount>? = null

    webBridge = gigya.createWebBridge()
    webBridge?.attachTo(webview, object: GigyaPluginCallback<GigyaAccount>() {}, null)
  }
}
