/*!For license information please see 39337-c8f0be91e8153baafa8c.js.LICENSE.txt*/(self.webpackChunkSHEIN_W=self.webpackChunkSHEIN_W||[]).push([[39337,95031,83783,76953],{876475:function(t,e,n){"use strict";n.d(e,{q:function(){return o}});var r=n(710311);const o=(0,r.defineComponent)({setup(t,{slots:e}){const n=(0,r.ref)(!1);return(0,r.onMounted)((()=>{n.value=!0})),()=>n.value&&e.default?e.default():null}})},195031:function(t,e,n){"use strict";n.d(e,{default:function(){return f}});const r="@@InfiniteScroll",o=function(t){return t===window?Math.max(window.pageYOffset||0,document.documentElement.scrollTop):t.scrollTop},i=document.defaultView.getComputedStyle,s=function(t){return t===window?o(window):t.getBoundingClientRect().top+o(window)},c=function(t){let e=t.parentNode;for(;e;){if("HTML"===e.tagName)return!0;if(11===e.nodeType)return!1;e=e.parentNode}return!1},u=function(){if(this.binded)return;this.binded=!0;let t=a,e=t.el,n=e.getAttribute("infinite-scroll-throttle-delay"),r=200;n&&(r=Number(t.vm[n]||n),(isNaN(r)||r<0)&&(r=200)),t.throttleDelay=r,t.scrollEventTarget=function(t){let e=t;for(;e&&"HTML"!==e.tagName&&"BODY"!==e.tagName&&1===e.nodeType;){const t=i(e).overflowY;if("scroll"===t||"auto"===t)return e;e=e.parentNode}return window}(e),t.scrollListener=function(t,e){let n,r,o,i,s;const c=function(){t.apply(i,s),r=n};return function(){if(i=this,s=arguments,n=Date.now(),o&&(clearTimeout(o),o=null),r){let t=e-(n-r);t<0?c():o=setTimeout((()=>{c()}),t)}else c()}}(l.bind(t),t.throttleDelay),t.scrollEventTarget.addEventListener("scroll",t.scrollListener);let o=e.getAttribute("infinite-scroll-disabled"),s=!1;o&&(this.vm.$watch(o,(function(e){t.disabled=e,!e&&t.immediateCheck&&setTimeout((()=>{l.call(t)}))})),s=Boolean(t.vm[o])),t.disabled=s;let c=e.getAttribute("infinite-scroll-distance"),u=0;c&&(u=Number(t.vm[c]||c),isNaN(u)&&(u=0)),t.distance=u;let f=e.getAttribute("infinite-scroll-immediate-check"),d=!0;f&&(d=Boolean(t.vm[f])),t.immediateCheck=d,d&&setTimeout((()=>{l.call(t)}));let h=e.getAttribute("infinite-scroll-listen-for-event");h&&t.vm.$on(h,(function(){setTimeout((()=>{l.call(t)}))}))},l=function(t){let e=a.scrollEventTarget,n=a.el,r=a.distance;if(!0!==t&&a.disabled)return;let i=o(e),c=i+function(t){return t===window?document.documentElement.clientHeight:t.clientHeight}(e),u=!1;if(e===n)u=e.scrollHeight-c<=r;else{u=c+r>=s(n)-s(e)+n.offsetHeight+i}u&&a.expression&&this.expression()};let a=null;const f={mounted(t,e,n){t[r]={el:t,vm:e.instance,expression:e.value},a=t[r];let o=arguments;t[r].vm.$nextTick((function(){c(t)&&u.call(t[r],o),t[r].bindTryCount=0;const e=function(){t[r].bindTryCount>10||(t[r].bindTryCount++,c(t)?u.call(t[r],o):setTimeout(e,50))};e()}))},unmounted(t){t&&t[r]&&t[r].scrollEventTarget&&t[r].scrollEventTarget.removeEventListener("scroll",t[r].scrollListener)},beforeUnmount(t){t&&t[r]&&t[r].scrollEventTarget&&t[r].scrollEventTarget.removeEventListener("scroll",t[r].scrollListener)}};f.install=t=>{t.directive("InfiniteScroll",f)}},306636:function(t){var e={utf8:{stringToBytes:function(t){return e.bin.stringToBytes(unescape(encodeURIComponent(t)))},bytesToString:function(t){return decodeURIComponent(escape(e.bin.bytesToString(t)))}},bin:{stringToBytes:function(t){for(var e=[],n=0;n<t.length;n++)e.push(255&t.charCodeAt(n));return e},bytesToString:function(t){for(var e=[],n=0;n<t.length;n++)e.push(String.fromCharCode(t[n]));return e.join("")}}};t.exports=e},401048:function(t){var e,n;e="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",n={rotl:function(t,e){return t<<e|t>>>32-e},rotr:function(t,e){return t<<32-e|t>>>e},endian:function(t){if(t.constructor==Number)return 16711935&n.rotl(t,8)|4278255360&n.rotl(t,24);for(var e=0;e<t.length;e++)t[e]=n.endian(t[e]);return t},randomBytes:function(t){for(var e=[];t>0;t--)e.push(Math.floor(256*Math.random()));return e},bytesToWords:function(t){for(var e=[],n=0,r=0;n<t.length;n++,r+=8)e[r>>>5]|=t[n]<<24-r%32;return e},wordsToBytes:function(t){for(var e=[],n=0;n<32*t.length;n+=8)e.push(t[n>>>5]>>>24-n%32&255);return e},bytesToHex:function(t){for(var e=[],n=0;n<t.length;n++)e.push((t[n]>>>4).toString(16)),e.push((15&t[n]).toString(16));return e.join("")},hexToBytes:function(t){for(var e=[],n=0;n<t.length;n+=2)e.push(parseInt(t.substr(n,2),16));return e},bytesToBase64:function(t){for(var n=[],r=0;r<t.length;r+=3)for(var o=t[r]<<16|t[r+1]<<8|t[r+2],i=0;i<4;i++)8*r+6*i<=8*t.length?n.push(e.charAt(o>>>6*(3-i)&63)):n.push("=");return n.join("")},base64ToBytes:function(t){t=t.replace(/[^A-Z0-9+\/]/gi,"");for(var n=[],r=0,o=0;r<t.length;o=++r%4)0!=o&&n.push((e.indexOf(t.charAt(r-1))&Math.pow(2,-2*o+8)-1)<<2*o|e.indexOf(t.charAt(r))>>>6-2*o);return n}},t.exports=n},935108:function(t){"use strict";t.exports=function(t,e){return e||(e={}),t?(t=String(t.__esModule?t.default:t),/^['"].*['"]$/.test(t)&&(t=t.slice(1,-1)),e.hash&&(t+=e.hash),/["'() \t\n]|(%20)/.test(t)||e.needQuotes?'"'.concat(t.replace(/"/g,'\\"').replace(/\n/g,"\\n"),'"'):t):t}},513149:function(t){function e(t){return!!t.constructor&&"function"==typeof t.constructor.isBuffer&&t.constructor.isBuffer(t)}t.exports=function(t){return null!=t&&(e(t)||function(t){return"function"==typeof t.readFloatLE&&"function"==typeof t.slice&&e(t.slice(0,0))}(t)||!!t._isBuffer)}},318036:function(t,e,n){"use strict";n.d(e,{$:function(){return l}});var r=n(382358),o=n(343957),i=n(35847),s=n(149500),c=new WeakMap;class u{constructor(){(0,o.Z)(this,c,{writable:!0,value:{}}),(0,r.Z)(this,"globalLocals",void 0)}getData(t,e,n){var r,o;return void 0!==(null==n?void 0:n[t])?null==n?void 0:n[t]:void 0!==(null===(r=this.globalLocals)||void 0===r?void 0:r[t])?null===(o=this.globalLocals)||void 0===o?void 0:o[t]:void 0!==(0,i.Z)(this,c)[t]?(0,i.Z)(this,c)[t]:void this.log("error",`Missing necessary data '${t}'. Use 'setData' to inject data before calling '${e}'.`)}setData(t,e){void 0!==(0,i.Z)(this,c)[t]&&(this.log("warn","overwrite!"),this.log("warn",`old [${t}]: `,(0,i.Z)(this,c)[t]),this.log("warn",`new [${t}]: `,e)),(0,i.Z)(this,c)[t]=e}assignData(t,e){if(!(0,i.Z)(this,c)[t])return this.log("warn",`data ${t} not found!`);Object.assign((0,i.Z)(this,c)[t],e)}log(t,...e){var n;if(s[t]("[sCommonController]",...e),"production"!==(null===(n=this.globalLocals)||void 0===n?void 0:n.NODE_SERVER_ENV)&&"error"===t)throw new Error("[sCommonController]".concat(...e.map((t=>String(t)))))}registLocals(t){this.globalLocals=t}}const l="undefined"!=typeof window?window._sCommonController?window._sCommonController:window._sCommonController=new u:new u},568013:function(t,e,n){"use strict";n.d(e,{Il:function(){return r},M2:function(){return s},_v:function(){return c},aj:function(){return a},c1:function(){return o},g8:function(){return i},k7:function(){return l},pR:function(){return u}});const r=/^http(s)?:\/\/(.*?)\//g,o=/[><#@$\s'"%+&]|[\u4e00-\u9fa5]/g,i=/\s*[#%&]\s*/g,s=/-{2,}/g,c=/\.shein\.|\.romwe\./,u=/^([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,6}|[0-9]{1,3})(\]?)$/,l=/^(?=.{5,100}$)^[a-zA-Z0-9]+([-_.]?([a-zA-Z0-9])+[-_]?)*@([a-zA-Z0-9]+([_.-]?[a-zA-Z0-9]+)*\.)([a-zA-Z]{2,6}|[0-9]{1,3}){1}$/,a=/(((^https?:(?:\/\/)?)(?:[-;:&=+$,\w]+@)?[A-Za-z0-9.-]+(?::\d+)?|(?:www.|[-;:&=+$,\w]+@)[A-Za-z0-9.-]+)((?:\/[+~%/.\w-_]*)?\??(?:[-+=&;%@.\w_]*)#?(?:[\w]*))?)$/},611575:function(t,e,n){"use strict";n.d(e,{W:function(){return s}});var r=n(382358),o=n(477606);const i=3600;class s{constructor(t){(0,r.Z)(this,"timeObj",void 0),(0,r.Z)(this,"seconds",0),(0,r.Z)(this,"timestamp",0),(0,r.Z)(this,"timeId",null),t&&this.reset(t),this.timeObj=this.formatTime()}start(t){t&&this.reset(t),this.seconds&&(this.timestamp=this.seconds+Math.floor(Date.now()/1e3),this.seconds=0),this.timeId&&clearInterval(this.timeId),this.timeId=setInterval((()=>this.countTime()),1e3),this.countTime()}clear(){this.start({seconds:0,dayMode:!1,countFunc:null,endFunc:null})}reset({seconds:t,timestamp:e,dayMode:n,countFunc:r,endFunc:o}){"number"==typeof e?(this.timestamp=Math.floor(e)>Math.floor(Date.now()/1e3)?Math.floor(e):0,this.seconds=0):"number"==typeof t&&(this.seconds=t>=1?Math.floor(t):0,this.timestamp=0),this.dayMode="boolean"==typeof n?n:this.dayMode,this.countFunc=void 0!==r?r:this.countFunc,this.endFunc=void 0!==o?o:this.endFunc}countTime(){var t,e;Object.assign(this.timeObj,this.formatTime()),null!==(t=this.countFunc)&&void 0!==t&&t.call(this,this.timeObj),this.timestamp||(this.timeId&&clearInterval(this.timeId),this.timeId=null,null===(e=this.endFunc)||void 0===e||e.call(this))}formatTime(){let t;if(this.seconds)t=this.seconds;else if(this.timestamp){const e=this.timestamp-Math.floor(Date.now()/1e3);e<=0?this.timestamp=t=0:t=e}else t=0;const e=this.dayMode?Math.floor(t/86400):0,n=this.dayMode?Math.floor(t%86400/i):Math.floor(t/i),r=Math.floor(t%i/60),s=Math.floor(t%60);return{D:(0,o.Y)(e),H:(0,o.Y)(n),M:(0,o.Y)(r),S:(0,o.Y)(s)}}}},984058:function(t,e,n){"use strict";n.d(e,{R:function(){return f}});const r=/^(\d{4})[-/]?(\d{1,2})?[-/]?(\d{0,2})[Tt\s]*(\d{1,2})?:?(\d{1,2})?:?(\d{1,2})?[.:]?(\d+)?$/,o=/\[([^\]]+)]|Y{1,4}|M{1,4}|D{1,2}|d{1,4}|H{1,2}|h{1,2}|a|A|m{1,2}|s{1,2}|Z{1,2}|SSS/g,i=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],s=["January","February","March","April","May","June","July","August","September","October","November","December"];function c(t,e,n){const r=String(t);return!r||r.length>=e?t:""+Array(e+1-r.length).join(n)+t}function u(t,e,n,r){return(null==t?void 0:t[e])||(null==n?void 0:n[e].slice(0,r))}function l(t,e){return c(t%12||12,e,"0")}function a(t,e){const n=t<12?"AM":"PM";return e?n.toLowerCase():n}class f{constructor(t){const e=this.$d=function(t){if("string"==typeof t&&!/Z$/i.test(t)){const e=t.match(r);if(e){const t=+e[2]-1||0,n=+(e[7]||"0").substring(0,3);return new Date(+e[1],t,+e[3]||1,+e[4]||0,+e[5]||0,+e[6]||0,n)}}return new Date(t)}(t);this.$y=e.getFullYear(),this.$M=e.getMonth(),this.$D=e.getDate(),this.$W=e.getDay(),this.$H=e.getHours(),this.$m=e.getMinutes(),this.$s=e.getSeconds(),this.$ms=e.getMilliseconds()}format(t){if("Invalid Date"===this.$d.toString())return"Invalid Date";const{$H:e,$m:n,$M:r}=this,f={YY:String(this.$y).slice(-2),YYYY:this.$y,M:r+1,MM:c(r+1,2,"0"),MMM:u(void 0,r,s,3),MMMM:u(s,r),D:this.$D,DD:c(this.$D,2,"0"),d:String(this.$W),dd:u(void 0,this.$W,i,2),ddd:u(void 0,this.$W,i,3),dddd:i[this.$W],H:String(e),HH:c(e,2,"0"),h:l(e,1),hh:l(e,2),a:a(e,!0),A:a(e,!1),m:String(n),mm:c(n,2,"0"),s:String(this.$s),ss:c(this.$s,2,"0"),SSS:c(this.$ms,3,"0")};return t.replace(o,(function(t,e){return e||f[t]}))}valueof(){return this.$d.getTime()}}},281883:function(t,e,n){"use strict";n.d(e,{P:function(){return s}});var r=n(984058),o=n(318036);const i={"A-1":"YYYY/MM/DD","A-2":"MM/DD/YYYY","A-3":"DD/MM/YYYY","A-4":"DD-MM-YYYY","A-5":"YYYY-MM-DD","A-6":"YY/MM/DD","B-1":"YYYY MMM DD","B-2":"MMM DD YYYY","B-3":"DD MMM YYYY","C-1":"dddd MM DD","C-2":"dddd DD MM","D-1":"MM/DD","D-2":"DD/MM","D-3":"DD-MM","D-4":"MM-DD","D-5":"MMM DD","D-6":"DD MMM","E-1":"MMM DD YYYY HH:mm:ss","E-2":"DD-MM-YYYY HH:mm:ss","E-3":"YYYY-MM-DD HH:mm:ss","E-4":"DD/MM/YYYY HH:mm:ss","E-5":"MM/DD/YYYY HH:mm:ss","E-6":"DD MMM YYYY HH:mm:ss","E-7":"YYYY/MM/DD HH:mm:ss","F-1":"MMM DD YYYY HH:mm","F-2":"DD-MM-YYYY HH:mm","F-3":"YYYY-MM-DD HH:mm","F-4":"DD/MM/YYYY HH:mm","F-5":"MM/DD/YYYY HH:mm","F-6":"DD MMM YYYY HH:mm","F-7":"YYYY/MM/DD HH:mm","G-1":"HH:mm:ss","G-2":"HH:mm","H-1":"dddd"},s=({time:t,sDateMap:e={},mapType:n="SiteUID",multiLang:s=!0,defaultCode:c="A-2"},u)=>{const l=Object.keys(e);let a;if(l.length){const t=o.$.getData(n,"timeTransformer",u);a=t&&l.find((n=>{var r;return null===(r=e[n])||void 0===r?void 0:r.includes(t)}))||c}else a=c;if(!a)return"";const f=i[a],d=new r.R(t).format(f);return s?function(t,e,n){if(!/(dddd|MMM)/.test(e))return t;const r=o.$.getData("dateLangMap","getLocalDateText",n);if(!r)return t;return Object.keys(r).forEach((e=>t=t.replace(e,r[e]))),t}(d,f,u):d}},272569:function(t,e,n){"use strict";n.d(e,{D:function(){return r}});const r=({func:t,wait:e=0,options:n={}})=>{const{leading:r=!1}=n;let o,i,s;const c=function(...n){return new Promise((c=>{if(s=this,o&&clearTimeout(o),r){const r=!o;o=setTimeout((()=>{o=null}),e),r&&(i=t.apply(s,n),c(i))}else o=setTimeout((()=>{i=t.apply(s,n),c(i),o=s=null}),e)}))};return c.cancel=function(){o&&clearTimeout(o),o=s=null},c}},174865:function(t,e,n){"use strict";function r(t){return t&&Array.isArray(t)}n.d(e,{k:function(){return r}})},188141:function(t,e,n){"use strict";n.d(e,{x:function(){return s}});var r=n(174865),o=n(636400),i=n(76520);function s(t){return null===t||((0,r.k)(t)||(0,o.H)(t)?0===t.length:t instanceof Map||t instanceof Set?0===t.size:!(0,i.K)(t)||0===Object.keys(t).length)}},199147:function(t,e,n){"use strict";n.d(e,{h:function(){return o}});var r=n(882065);function o(t){return(0,r.is)(t,"Number")}},636400:function(t,e,n){"use strict";n.d(e,{H:function(){return o}});var r=n(882065);function o(t){return(0,r.is)(t,"String")}},840035:function(t,e,n){"use strict";n.d(e,{c:function(){return o}});var r=n(318036);const o=({val:t,symbol:e="%",percent:n},o)=>{const i=r.$.getData("GB_cssRight","discountPercentNum",o),s=n?(c=Number(t),Math.floor(100-Math.round(100*c+Number.EPSILON))):t;var c;return i?`${e}${s}`:`${s}${e}`}},477606:function(t,e,n){"use strict";n.d(e,{Y:function(){return r}});const r=t=>t<10?`0${t}`:`${t}`},344945:function(t,e,n){"use strict";n.d(e,{Q:function(){return o}});var r=n(568013);const o=({email:t,signUp:e})=>e?r.k7.test(t):r.pR.test(t)},85461:function(t,e,n){"use strict";n.d(e,{H:function(){return r}});const r=({imgs:t,cb:e})=>{const n=t.length;let r=0;const i=t.map((t=>new Promise((i=>{if(!t)return i("");o(t).then((()=>{r++;const o=+(r/n).toFixed(2);e&&e({image:t,progress:o}),i(t)}))}))));return Promise.all(i)};function o(t,e=!1){return new Promise((n=>{const r=new Image;r.src=t,r.onload=()=>{n()},r.onerror=()=>{e?n():o(t,!0).then((()=>n()))}}))}},397417:function(t,e,n){"use strict";n.d(e,{f:function(){return c}});var r=n(318036);let o=!1;const i="undefined"==typeof window,s=["img.shein.com/images/","img.shein.com/images2/","img.shein.com/images3/","img.romwe.com/images/","img.romwe.com/images2_rw/","img.romwe.com/images3_rw/","img.ltwebstatic.com","file.ltwebstatic.com"];i||(()=>{const t=window.navigator.userAgent.toLowerCase();/iphone|ipad|macintosh/.test(t)&&/version\//.test(t)?function(){const t=new Image;t.onload=()=>{o=!!(t.width>0&&t.height>0)},t.onerror=()=>{o=!1},t.src="data:image/webp;base64,UklGRh4AAABXRUJQVlA4TBEAAAAvAAAAAAfQ//73v/+BiOh/AAA="}():o=function(){const t=document.createElement("canvas");return t&&0===t.toDataURL("image/webp").indexOf("data:image/webp")}()})();const c=({img:t,useWebp:e=!0,isImgCenter:n=!1},c)=>t?(t=t.replace("http:","").replace("https:",""),t=/^\/\/.*/.test(t)?t:n?r.$.getData("IMG_SERVICE_PATH","transformImg",c)+t:r.$.getData("IMG","transformImg",c)+t,i?t:e&&o?(({img:t})=>s.some((e=>t.indexOf(e)>-1))?t.replace(/\b(\.jpg|\.png)\b/,".webp"):t)({img:t}):t):"data:image/gif;base64,R0lGODlhAQABAAAAACwAAAAAAQABAAA="},515033:function(t,e,n){"use strict";n.d(e,{l:function(){return o}});const r=t=>t.replace(/&lt;/g,"<").replace(/&gt;/g,">").replace(/&amp;/g,"&").replace(/&nbsp;/g," ").replace(/&#39;/g,"'").replace(/&quot;/g,'"'),o=({text:t,isHtml:e})=>{if("string"==typeof t&&t){if(e)return r(t);if("undefined"!=typeof document){let e=document.createElement("div");e.innerHTML=t;const n=e.innerText||e.textContent||"";return e=null,r(n)}}return""}},631274:function(t,e,n){"use strict";n.d(e,{A:function(){return o}});var r=n(568013);const o=({href:t,activity:e})=>{if(e)return t.replace(r.g8,"-");const n=t&&t.match(r.Il);if(n&&!n[0].match(r._v))return t;const o=t.indexOf("://")>0?t.indexOf("://")+2:0,i=t.indexOf("?",o)>0?t.indexOf("?",o):t.length;return(t.substring(0,o)+t.substring(o,i).replace(r.c1,"-")+t.substring(i,t.length)).replace(r.M2,"-")}},465163:function(t,e,n){"use strict";n.d(e,{X:function(){return r}});const r=(...t)=>{let e=t[t.length-1]||"";e=String(e);for(let n=0;n<t.length-1;n++){const r=new RegExp("\\{"+n+"\\}","gm"),o=String(t[n]);e=o&&o.indexOf("$")>=0?e.replace(r,o.replace(/\$/g,"$$$$")):e.replace(r,o)}return e}},214192:function(t,e,n){"use strict";n.d(e,{S:function(){return r}});const r=t=>{const e=t.indexOf("#");-1!==e&&(t=t.slice(0,e));const n=t.indexOf("?");return-1===n?"":t.slice(n+1)}},244945:function(t,e,n){"use strict";n.d(e,{C:function(){return i}});var r=n(31530),o=n(872342);const i=({mergeObj:t,url:e,options:n})=>{if(!t||"string"!=typeof e||t&&!Object.keys(t).length)return e;const i=e.split("?"),s=null==i?void 0:i[0],c=null==i?void 0:i[1],u=Object.assign({},(0,r.d)(c),t);return`${s}?${(0,o.f)({queryObj:u,options:n})}`}},872342:function(t,e,n){"use strict";n.d(e,{f:function(){return r}});const r=({queryObj:t,options:e={sort:void 0}})=>{if(!t)return"";const n=Object.keys(t);return e&&!1!==e.sort&&n.sort(e.sort),n.map((e=>{let n=t[e];if("string"!=typeof n&&"number"!=typeof n||(n=encodeURIComponent(n)),void 0===n)return"";if(null===n)return e;if(Array.isArray(n)){const t=[];return n.slice().forEach((n=>{void 0!==n&&(null===n?t.push(e):t.push(e+"="+encodeURIComponent(n)))})),t.join("&")}return e+"="+n})).filter((t=>t.length>0)).join("&")}},680986:function(t,e,n){"use strict";n.d(e,{FH:function(){return m},Mg:function(){return y},Mj:function(){return p},Zi:function(){return g}});var r=n(149500),o=Object.defineProperty,i=(t,e,n)=>(((t,e,n)=>{e in t?o(t,e,{enumerable:!0,configurable:!0,writable:!0,value:n}):t[e]=n})(t,"symbol"!=typeof e?e+"":e,n),n);const s=t=>"[object Object]"===Object.prototype.toString.call(t),c=t=>{"undefined"==typeof window?"function"!=typeof requestIdleCallback?setTimeout(t,200):requestIdleCallback(t):t()},u=t=>Array.from(new Set(Array.isArray(t)?t:[])),l="abtMap",a="getUserAbtResult",f="getUserAbtResultForAnalysis",d=new class{constructor(){i(this,"queue"),i(this,"fetching"),i(this,"abtServerFunction"),this.queue=[],this.fetching=!0,this.abtServerFunction=()=>{}}use(t){this.abtServerFunction=t}intercept(){this.fetching=!0}getABTData(){let t=this.abtServerFunction?this.abtServerFunction():{};return t instanceof Map&&(t=Object.fromEntries(t)),t}trigger(t){this.queue.push(t),this.fetching||this.notify()}notify(){this.fetching=!1,this.queue.forEach((t=>t(this.getABTData()))),this.queue=[]}},h={config:void 0,preloadFetching:!1,currentFetchNum:0,changePageNum:0};function m(t){if(!s(t))throw new Error("config must be a object");if(s(h.config))throw new Error("abtRouter has been instantiated");h.config=t,d.use((()=>{var e;return(null==(e=t.abtServer)?void 0:e[l])||new Map}))}async function p({routeName:t}){var e,n;if(!s(h.config))throw new Error("请先调用 instanceAbtRouter 方法");d.intercept(),h.changePageNum+=1;const o=1===h.changePageNum,{abtServer:i,options:l=[]}=h.config,f=function(t,e){const n={posKeys:[],newPosKeys:[]};return t.forEach((t=>{e&&t.routeName===e&&(n.posKeys.push(...(null==t?void 0:t.posKeys)||[]),n.newPosKeys.push(...(null==t?void 0:t.newPosKeys)||[]))})),n}(l,t),m=[...f.posKeys],p=[...f.newPosKeys];o&&(m.push(...(null==(e=h.config)?void 0:e.posKeys)||[]),p.push(...(null==(n=h.config)?void 0:n.newPosKeys)||[])),await async function({abtServer:t,posKeys:e,newPosKeys:n}){try{h.currentFetchNum+=1,(e.length||n.length)&&await t[a]({posKeys:u(e),newPosKeys:u(n)})}catch(t){!("undefined"==typeof window)&&r.error(`[ABT_ROUTE]: abtRequest error [${t}]`)}finally{h.currentFetchNum-=1,h.currentFetchNum<1&&d.notify()}}({abtServer:i,posKeys:m,newPosKeys:p}),c((()=>{!async function(t){const{options:e,preloadModel:n,abtServer:r}=h.config||{};if(!n||h.preloadFetching)return;const o=function(t,e){const n={posKeys:[],newPosKeys:[]};return t.forEach((t=>{t.routeName!==e&&t.preload&&(n.posKeys.push(...(null==t?void 0:t.posKeys)||[]),n.newPosKeys.push(...(null==t?void 0:t.newPosKeys)||[]))})),n}(e,t);h.preloadFetching=!0,(o.posKeys.length||o.newPosKeys.length)&&await r[a]({posKeys:u(o.posKeys),newPosKeys:u(o.newPosKeys)})}(t)}))}function g(){return"undefined"==typeof window?Promise.resolve({}):new Promise((t=>d.trigger(t)))}function y(t){var e;return"undefined"==typeof window?{}:(null==(e=h.config)?void 0:e.abtServer[f](t))||{}}},431929:function(t,e,n){"use strict";n.d(e,{N:function(){return c}});var r=n(149500),o=Object.defineProperty,i=(t,e,n)=>(((t,e,n)=>{e in t?o(t,e,{enumerable:!0,configurable:!0,writable:!0,value:n}):t[e]=n})(t,"symbol"!=typeof e?e+"":e,n),n);class s{constructor({schttp:t}){i(this,"schttp"),i(this,"requestQueue"),this.schttp=t,this.requestQueue=new Map}findQueueKey(t){let e=t;for(const n of this.requestQueue.keys()){const r=n.split(",");if(t.split(",").every((t=>r.includes(t)))){e=n;break}}return e}request({posKeys:t,newPosKeys:e,requestPoskeys:n}){return new Promise(((r,o)=>{const i=this.findQueueKey(n),s=this.requestQueue.get(i)||[];s.push({resolve:r,reject:o}),this.requestQueue.set(i,s),1===s.length&&this.fetchAbt({posKeys:t,newPosKeys:e,requestPoskeys:i})}))}async fetchAbt({posKeys:t,newPosKeys:e,requestPoskeys:n}){let o={};try{o=await this.schttp({posKeys:t,newPosKeys:e})}catch(t){r.error(t)}finally{const t=this.requestQueue.get(n)||[];this.requestQueue.delete(n),null==t||t.forEach((({resolve:t})=>{t(o)}))}return o}}class c{constructor({schttp:t}){i(this,"abtMap"),i(this,"request"),this.request=new s({schttp:t}),this.abtMap=new Map}async getUserAbtResult({posKeys:t,newPosKeys:e}={}){const n=`${t||""}`.split(",").filter((t=>t)),r=`${e||""}`.split(",").filter((t=>t)),o=[...new Set([...r,...n])];if(o.every((t=>this.abtMap.has(t))))return u(o,this.abtMap);if(o.length){const t=r.filter((t=>!this.abtMap.has(t))),e=n.filter((t=>!this.abtMap.has(t))),o=[...t,...e],i=await this.request.request({requestPoskeys:o.join(),newPosKeys:t.join(),posKeys:e.join()});o.forEach((t=>{this.abtMap.set(t,i[t]||{})}))}return u(o,this.abtMap)}getUserAbtResultForAnalysis({posKeys:t,abtInfo:e},n){if(!t&&!e||Array.isArray(t)&&!t.length)return{};var r;r=e,"[object Object]"===Object.prototype.toString.call(r)&&(e=Object.keys(e).reduce(((t,n)=>{const r=e[n]||{};return t[r.poskey||r.posKey||n]=r,t}),{}));return function({posKeys:t,abtInfo:e={}},n){if(t){const n=("string"==typeof t?t.split(","):t).filter((t=>t));if(!n.length)return{};e=function(t,e){return t.reduce(((t,n)=>(e[n]&&(t[n]=e[n]),t)),{})}(n,e)}const r=Object.entries(e);if(!r.length)return{};const o=[],i=[],s=[];return r.forEach((([t,e])=>{var r;if(e&&!Array.isArray(e.child))return;n&&(e=n(e,t));const c=function(t,{child:e=[]}){const n=e.reduce(((e,n)=>(n.eid&&n.bid&&e.push(`${t}\`${n.eid}\`${n.bid}`),e)),[]).join(",");return n}(t=e.posKey||e.poskey||t,e);c&&o.push(c);const{bid:u="",type:l=""}=(null==(r=e.child)?void 0:r[0])||{};l&&u&&(i.push(`${t}_${l}_${u}`),s.push(`TP_${t}-BT_${l}-BI_${u}`))})),{sa:o.join(","),ga:i.join("-"),sc:s.join(",")}}({posKeys:t,abtInfo:e||Object.fromEntries(this.abtMap.entries())},n)}useState(t,e){t?this.abtMap.set(t,e):r.error("请添加当前poskey")}}function u(t,e){return t.length?t.reduce(((t,n)=>(t[n]=e.get(n)||{},t)),{}):Object.fromEntries(e.entries())}},83783:function(t,e,n){"use strict";n.d(e,{Z:function(){return l}});var r=n(710311),o=n(149500);const i='<svg style="width: 1rem;"/>';let s=null;function c(t,e){t.hasAttribute("fill")&&t.setAttribute("fill",e),t.hasAttribute("stroke")&&t.setAttribute("stroke",e)}const u={name:{type:String,default:""},customStyle:{type:Object,default:()=>{}},size:{type:String,default:"16px"},width:{type:String,default:""},height:{type:String,default:""},color:{type:String,default:"currentColor"},isRotate:{type:Boolean,default:!1},isResponsiveName:{type:Boolean,default:!1}},l=(0,r.defineComponent)({name:"Icon",props:u,setup:(t,{emit:e})=>{const n=(0,r.ref)();async function u(){const e=function(t){return"sh_pc_"+t}(t.name),r=await async function(t){if(typeof window>"u")return;const e=`https://shein.ltwebstatic.com/svgicons/icons/${t}.svg`;return e?"string"!=typeof e?(o.warn("=====Svg图标地址不是字符串====="),i):(window.svg_icon_map||(window.svg_icon_map=new Map),window.svg_icon_map.get(t)||window.svg_icon_map.set(t,new Promise(((t,n)=>{const r=new XMLHttpRequest;r.open("get",e),r.send(),r.onreadystatechange=()=>{if(4===r.readyState)if(200===r.status)try{t(r.responseText)}catch{n(new Error("CDN service error"))}else n(new Error("CDN service error"))}}))),window.svg_icon_map.get(t)):(o.warn("=====Svg图标地址不存在====="),i)}(e);return((t,e,{color:n="",isColor:r=!1})=>{if(typeof window<"u"&&null===s&&(s=new DOMParser),e){const o=s.parseFromString(t,"text/html").querySelector("svg");if(o&&o.innerHTML){for(const t of o.attributes)e.setAttribute(t.name,t.value);if(r){const t="parent"+Math.floor(1e4*Math.random()),e="url(#"+t+")",n="url(#pattern0)",r="pattern0";o.querySelectorAll("path").forEach((t=>{t.getAttribute("fill")===n&&c(t,e)})),o.querySelectorAll("pattern").forEach((e=>{e.getAttribute("id")===r&&e.setAttribute("id",t)}))}else{const t=o.querySelectorAll("path"),e=o.querySelectorAll("circle");t.forEach((t=>{c(t,n)})),e.forEach((t=>{c(t,n)}))}e.innerHTML=o.innerHTML}}})(r,n.value,{color:t.color,isColor:e.endsWith("_color")}),r}return(0,r.onMounted)((()=>{u(),t.isResponsiveName&&(0,r.watch)((()=>t.name),((t,e)=>{t!==e&&u()}))})),{getStyle:function(){const{customStyle:e,isRotate:n,color:r}=t;let o={verticalAlign:"middle",display:"inline-block",lineHeight:0,color:r,...e};return n&&(o=Object.assign(o,{transform:"rotateY(180deg)"})),o},svgRef:n}}});l.render=function(t,e,n,o,i,s){return(0,r.openBlock)(),(0,r.createElementBlock)("span",{class:"sui-icon-common__wrap",style:(0,r.normalizeStyle)(t.getStyle())},[((0,r.openBlock)(),(0,r.createElementBlock)("svg",{style:(0,r.normalizeStyle)({width:t.width||t.size,height:t.height||t.size}),ref:"svgRef"},null,4))],4)}},533941:function(t,e,n){"use strict";n.d(e,{Z:function(){return o}});var r=n(710311);const o={name:"sui_icon_save_32px",props:{customStyle:{type:Object,default:()=>{}},size:{type:String,default:"16px"},width:{type:String,default:""},height:{type:String,default:""},color:{type:String,default:"currentColor"},isRotate:{type:Boolean,default:!1}},computed:{getStyle(){const{customStyle:t,isRotate:e}=this;let n={verticalAlign:"middle",display:"inline-block",lineHeight:0,...t};return e&&(n=Object.assign(n,{transform:"rotateY(180deg)"})),n}},methods:{handleClick(){this.$emit("click")}}},i=["fill"];o.render=function(t,e,n,o,s,c){return(0,r.openBlock)(),(0,r.createElementBlock)("span",{style:(0,r.normalizeStyle)(c.getStyle)},[((0,r.openBlock)(),(0,r.createElementBlock)("svg",{style:(0,r.normalizeStyle)({width:n.width||n.size,height:n.height||n.size}),width:"32",height:"32",viewBox:"0 0 32 32",fill:"none",xmlns:"http://www.w3.org/2000/svg"},[(0,r.createElementVNode)("path",{"fill-rule":"evenodd","clip-rule":"evenodd",d:"M15.1294 5.99275C12.2066 3.27396 7.63256 3.33719 4.7865 6.18243C1.87575 9.09317 2.04569 13.2328 4.7865 16.7221C5.08879 17.1069 5.46081 17.5433 5.8835 18.0151L6.41384 18.5973C6.50587 18.6968 6.59963 18.7975 6.69496 18.8992L7.28453 19.521L7.90445 20.1613L8.54739 20.8142L9.53898 21.8034L10.0124 22.2696L11.0539 23.2833L12.2628 24.443L14.3607 26.4202L16 27.9356L18.4447 25.6661L20.9461 23.2833L22.4611 21.8034L23.4526 20.8142L24.0956 20.1613L24.7155 19.521L25.3051 18.8992C25.4004 18.7975 25.4942 18.6968 25.5862 18.5973L26.1165 18.0151C26.5392 17.5433 26.9113 17.1069 27.2135 16.7221C29.9543 13.2328 30.1243 9.09317 27.2135 6.18243C24.3028 3.27252 19.5847 3.27252 16.6739 6.18243L16 6.85633L15.3261 6.18243L15.1294 5.99275ZM18.2686 7.42416C20.4084 5.46936 23.729 5.52686 25.7995 7.59685C27.8871 9.68439 27.8525 12.6709 25.6408 15.4866C25.5975 15.5417 25.5524 15.5982 25.5055 15.6563L25.2025 16.0219L24.8571 16.4216L24.4709 16.8541L24.0451 17.3182L23.581 17.8127L23.08 18.3364L22.2622 19.1739L21.6748 19.765L20.5845 20.8444L19.7954 21.6138C18.73 22.6468 17.616 23.7046 16.5028 24.7451L16 25.213L14.6629 23.9621C13.5524 22.9155 12.4529 21.8618 11.4129 20.8417L10.9421 20.3782L10.0257 19.4645L9.42087 18.8516L8.85468 18.2687L8.32873 17.7173L7.84463 17.1991L7.40402 16.7154L7.00851 16.2677L6.65973 15.8576C6.55143 15.7274 6.4512 15.6036 6.35929 15.4866C4.14753 12.6709 4.11296 9.68439 6.20051 7.59685C8.33025 5.46772 11.7824 5.46772 13.9121 7.59685L16 9.68476L18.0881 7.59664L18.2686 7.42416Z",fill:n.color},null,8,i)],4))],4)}},779303:function(t,e,n){"use strict";n.d(e,{Z:function(){return o}});var r=n(710311);const o={name:"sui_icon_save_completed_32px",props:{customStyle:{type:Object,default:()=>{}},size:{type:String,default:"16px"},width:{type:String,default:""},height:{type:String,default:""},color:{type:String,default:"currentColor"},isRotate:{type:Boolean,default:!1}},computed:{getStyle(){const{customStyle:t,isRotate:e}=this;let n={verticalAlign:"middle",display:"inline-block",lineHeight:0,...t};return e&&(n=Object.assign(n,{transform:"rotateY(180deg)"})),n}},methods:{handleClick(){this.$emit("click")}}},i=["fill"];o.render=function(t,e,n,o,s,c){return(0,r.openBlock)(),(0,r.createElementBlock)("span",{style:(0,r.normalizeStyle)(c.getStyle)},[((0,r.openBlock)(),(0,r.createElementBlock)("svg",{style:(0,r.normalizeStyle)({width:n.width||n.size,height:n.height||n.size}),width:"32",height:"32",viewBox:"0 0 32 32",fill:"none",xmlns:"http://www.w3.org/2000/svg"},[(0,r.createElementVNode)("path",{"fill-rule":"evenodd","clip-rule":"evenodd",d:"M4.7865 6.18243C7.63256 3.33719 12.2066 3.27396 15.1294 5.99275L15.3261 6.18243L16 6.85633L16.6739 6.18243C19.5847 3.27252 24.3028 3.27252 27.2135 6.18243C30.1243 9.09317 29.9543 13.2328 27.2135 16.7221C26.9113 17.1069 26.5392 17.5433 26.1165 18.0151L25.5862 18.5973C25.4942 18.6968 25.4004 18.7975 25.3051 18.8992L24.7155 19.521L24.0956 20.1613L23.4526 20.8142L22.4611 21.8034L20.9461 23.2833L18.4447 25.6661L16 27.9356L14.3607 26.4202L12.2628 24.443L11.0539 23.2833L10.0124 22.2696L9.53898 21.8034L8.54739 20.8142L7.90445 20.1613L7.28453 19.521L6.69496 18.8992C6.59963 18.7975 6.50587 18.6968 6.41384 18.5973L5.8835 18.0151C5.46081 17.5433 5.08879 17.1069 4.7865 16.7221C2.04569 13.2328 1.87575 9.09317 4.7865 6.18243Z",fill:n.color},null,8,i)],4))],4)}},694039:function(t,e,n){"use strict";function r(t){return{all:t=t||new Map,on:function(e,n){var r=t.get(e);r?r.push(n):t.set(e,[n])},off:function(e,n){var r=t.get(e);r&&(n?r.splice(r.indexOf(n)>>>0,1):t.set(e,[]))},emit:function(e,n){var r=t.get(e);r&&r.slice().map((function(t){t(n)})),(r=t.get("*"))&&r.slice().map((function(t){t(e,n)}))}}}n.d(e,{Z:function(){return r}})}}]);