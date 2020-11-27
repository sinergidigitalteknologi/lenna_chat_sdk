# lenna chat sdk

This is a chat sdk from <a href="https://lenna.ai">Lenna</a> (PT Sinergi Digital Teknologi)



<h3><a id="user-content-gradle-setup" class="anchor" aria-hidden="true" href="#gradle-setup"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path></path></svg></a>Gradle Setup (Project)</h3>

<div class="highlight highlight-source-groovy-gradle"><pre><span class="pl-en">repositories</span> {
    maven { url <span class="pl-s"><span class="pl-pds">'</span>https://jitpack.io<span class="pl-pds">'</span></span> }
}
</div>

<h3><a id="user-content-gradle-setup" class="anchor" aria-hidden="true" href="#gradle-setup"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path></path></svg></a>Gradle Setup (Module)</h3>

<div class="highlight highlight-source-groovy-gradle"><pre>
defaultConfig {
    ...
    minSdkVersion 19
    ...
}
</br>
compileOptions {
    sourceCompatibility = '1.8'
    targetCompatibility = '1.8'
}
</br>
dependencies {
    implementation 'com.github.sinergidigitalteknologi:lenna_chat_sdk:1.0.0.7_NE'
}</pre></div>
</br>

<h3><a id="user-content-gradle-setup" class="anchor" aria-hidden="true" href="#gradle-setup"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path></path></svg></a>Usage </h3>

1. import ai.lenna.lennachatmodul.Chat in your activity

<div class="highlight highlight-source-js"><pre><span class="pl-k">import</span> ai.lenna.lennachatmodul.Chat;
</span></pre></div>

2. Call and set some configuration on Class Chat

<div class="highlight highlight-source-js"><pre><span class="pl-k"> 
Chat.setAppId("...");
Chat.setBotId("...");
Chat.setAppKey("...");
Chat.setUserName("...");
Chat.setIcon(...);
Chat.setIconBubleChat(...);
Chat.setGreetingMessage(...);
Chat.start(this);
Chat.setKeyFallBack("...");
Chat.setRequestMenuFAllback("...");
Chat.start(context);

</span></pre></div>

<h2><a id="user-content-gradle-setup" class="anchor" aria-hidden="true" href="#gradle-setup"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path></path></svg></a> Contributors </h2>

<table>
<thead>
<tr>
<th align="center"><a href="https://www.coolecho.net" rel="nofollow"><img src="https://avatars1.githubusercontent.com/u/37471218?s=400&u=cbded4af86184e5dbb08433876d7b37ff888d67e&v=4" width="100px;" style="max-width:100%;"><br><sub><b><a href="https://github.com/vikyyahya">Viky Yahya</a></b></sub></a><br><a href="https://github.com/vikyyahya"><g-emoji class="g-emoji" alias="computer" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4bb.png">💻</g-emoji></a> <a href="#" title="Design"><g-emoji class="g-emoji" alias="art" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f3a8.png">🎨</g-emoji></a> <a href="#" title="Documentation"><g-emoji class="g-emoji" alias="book" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4d6.png">📖</g-emoji></a> <a href="#"></a></th>
<th align="center"><a href="https://www.coolecho.net" rel="nofollow"><img src="https://avatars0.githubusercontent.com/u/12740572?s=460&u=e7f14bbe4aa2a00d3332e4ce1a60a34fe89eca6c&v=4" width="100px;" style="max-width:100%;"><br><sub><b><a href="https://github.com/ryanzulham">Ryan Zulham </a></b></sub></a><br><a href="https://github.com/ryanzulham"><g-emoji class="g-emoji" alias="computer" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4bb.png">💻</g-emoji></a> <a href="#" title="Design"><g-emoji class="g-emoji" alias="art" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f3a8.png">🎨</g-emoji></a> <a href="#" title="Documentation"><g-emoji class="g-emoji" alias="book" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4d6.png">📖</g-emoji></a> <a href="#" title="Tests"></a></th>

<th align="center"><a href="https://www.coolecho.net" rel="nofollow"><img src="https://avatars1.githubusercontent.com/u/23380230?s=400&u=e79f3c79c4b4eb95190d10edc9b458977819fadd&v=4" width="100px;" style="max-width:100%;"><br><sub><b><a href="https://github.com/sosiawan55">M Arief Sosiawan</a></b></sub></a><br><a href="https://github.com/sosiawan55"><g-emoji class="g-emoji" alias="computer" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4bb.png">💻</g-emoji></a> <a href="#" title="Design"><g-emoji class="g-emoji" alias="art" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f3a8.png">🎨</g-emoji></a> <a href="#" title="Documentation"><g-emoji class="g-emoji" alias="book" fallback-src="https://github.githubassets.com/images/icons/emoji/unicode/1f4d6.png">📖</g-emoji></a> <a href="#" title="Tests"></a></th>

</tr>
</thead>
</table>

