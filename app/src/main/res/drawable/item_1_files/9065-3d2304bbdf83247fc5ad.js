"use strict";(self.webpackChunkSHEIN_W=self.webpackChunkSHEIN_W||[]).push([[9065],{770457:function(e,n,t){t.d(n,{w:function(){return o}});const o=["worldpay-googlepay","adyen-googlepay","nuvei-googlepay"]},287272:function(e,n,t){t.d(n,{u:function(){return o}});const o={CHECKED:"1",UNCHECKED:"0"}},859104:function(e,n,t){t.d(n,{$3:function(){return C},A9:function(){return f},DH:function(){return _},DN:function(){return I},Gq:function(){return P},RL:function(){return v},Yy:function(){return w},ZX:function(){return E},c9:function(){return T},gY:function(){return g},h5:function(){return y},rZ:function(){return h},tT:function(){return b},uH:function(){return S},yg:function(){return m}});var o=t(843326),a=t(995692),i=t(457204),l=t(397417),s=t(853943),r=t(573543);const{SiteUID:c,appLanguage:u,lang:d,currency:p}=gbCommonInfo;function h(e={}){const{totalPrice:n={},grandTotalPrice:t={},tax:o=[],handling_fee:a={},onlinePayDiscountInfo:i={},extraPromotion:l={},newSubTotal:s={},newSubTotalWithoutVat:r={},...c}=e||{},u=e=>0!==Number((null==e?void 0:e.amount)||"")?null==e?void 0:e.amountWithSymbol:0;return{grandTotalPriceWithSymbol:u(t),totalPriceWithSymbol:u(n),handlingFee:u(a),newSubTotal:u(s),newSubTotalWithoutVat:u(r),onlinePayDiscountPrice:(e=>{const{discountPrice:n,isEnjoyedDiscount:t=0}=e||{};return 1==t&&0!==Number((null==n?void 0:n.amount)||"")?n.amountWithSymbol:0})((null==i?void 0:i.onlinePayDiscountInfo)||{}),marketFission:(e=>{const{amount:n={},isValidated:t=0}=e||{};return 1==t&&0!==Number((null==n?void 0:n.amount)||"")?null==n?void 0:n.amountWithSymbol:0})(null==l?void 0:l.marketFission),tax:o,onlinePayDiscountInfo:i,personalTaxes:null==o?void 0:o.filter((e=>{var n;return(null==e||null===(n=e.taxPrice)||void 0===n?void 0:n.amount)>0&&2===(null==e?void 0:e.taxPayType)})),companyTaxes:null==o?void 0:o.filter((e=>{var n;return(null==e||null===(n=e.taxPrice)||void 0===n?void 0:n.amount)>0&&1===(null==e?void 0:e.taxPayType)})),grandTotalPrice:t,...c}}async function f({billno:e}){const n=await(0,o.Z)({method:"POST",url:"/api/ocp/addPurchaseRelation/get",data:{billno:e}}).catch((()=>{}));return(null==n?void 0:n.info)||{}}function v(e,{isSync:n=!1,successCb:t,errorCb:o}={}){(0,i.B)({isAsync:!n,opt:{method:"POST",url:"/api/ocp/calculateMall/get",schttp:{needLogin:!0},data:e},successCb:t,errorCb:o})}async function g(){try{const{code:e,info:n={}}=await(0,o.Z)({url:"/api/ocp/payConfig/get"});if(300206==e)return{code:e,msg:"need login"};const t=await(0,o.Z)({url:"/ltspc/transformer/init",method:"POST",data:{SiteUID:c,appLanguage:u,lang:d,AppCurrency:p,TokenAes:null==n?void 0:n.aesToken,_csrf:null==n?void 0:n.csrfToken,siteFrom:"pc"}});return 0!=(null==t?void 0:t.code)?{code:t.code,msg:"",info:n}:{code:0,msg:"",info:n}}catch(e){return{code:-1,msg:""}}}async function y(e){var n;const t={billno:null==e?void 0:e.billno,good_sn:null==e?void 0:e.good_sn,goods_id:null==e?void 0:e.goods_id,mall_code:null==e?void 0:e.mall_code,quantity:null==e?void 0:e.quantity,sku_code:null==e?void 0:e.sku_code,country:null==e?void 0:e.country_code,payment_no:null==e?void 0:e.payment_no},{calculateMall:a={},addPurchaseRelation:i={},paymentList:r={},currencySupport:c={},abtInfo:u={},defaultPaymentInfo:d,ENCRYPT_KEY_URL:p,order_details:f={},cccTaxIncludedInfo:v={}}=await(0,o.Z)({method:"POST",url:"/api/ocp/checkoutInfo/get",data:t}).catch((()=>{})),g=h((null==a?void 0:a.info)||{}),y=function({addPurchaseRelation:e={},goods_thumb:n,quantity:t}){const{all_relation_order_list:o=[],merge_buy_tag:a,merge_buy_billno:i}=e||{};let s=[],r=[{order_goods_sum:t||1,goods_thumb:(0,l.f)({img:n})}];function c({order:e}){return e.map((e=>e.goods_list)).flat(1/0).map((e=>({...e,goods_thumb:(0,l.f)({img:e.goods_thumb})})))}s=c(0===a?{order:o}:{order:o.filter((e=>e.billno===i))});return{shipOrderGoods:s,addedOrderGoods:r}}({addPurchaseRelation:null==i?void 0:i.info,goods_thumb:null==e?void 0:e.goods_thumb,quantity:null==e?void 0:e.quantity});return(0,s.B)("aggregateQueryPopupInfo---",a,i,r,f),{priceSummary:g,goodsInfo:y,paymentMethods:(null==r||null===(n=r.info)||void 0===n?void 0:n.payments)||[],currencySupportList:c,abtInfo:u,defaultPaymentInfo:d||{},ENCRYPT_KEY_URL:p,calculateMall:a,isCalculateMallSuccess:0==(null==a?void 0:a.code)&&!!Object.keys((null==a?void 0:a.info)||{}).length,order_details:f,cccTaxIncludedTips:(null==v?void 0:v.taxIncludesTips)||""}}async function m(e){return new Promise(((n,t)=>{if(null==e||!e.shipCountry||null==e||!e.orderAmount)return t(e);(0,o.Z)({method:"post",url:"/api/checkout/channelSession/create",data:e}).then((e=>{var o;0==(null==e?void 0:e.code)?n((null==e||null===(o=e.info)||void 0===o?void 0:o.sessions)||[]):t(e)})).catch(t)}))}async function _(e){return await window._GB_DeviceFingerPrint.callFuncPromise(),(0,o.Z)({method:"post",url:"/api/ocp/order/create",data:e}).catch((()=>{}))}function P({isAsync:e,postData:n,successCb:t,errorCb:o}){window._GB_DeviceFingerPrint.callFunc((()=>{(0,i.B)({isAsync:e,opt:{method:"POST",url:"/api/ocp/order/create",schttp:{needLogin:!0},data:n},successCb:t,errorCb:o})}))}async function C({countryCode:e}={}){return await(0,o.Z)({method:"get",url:"/api/ocp/pubKey/get",params:{countryCode:e}}).catch((()=>{}))||{}}async function I(e){return(0,o.Z)({method:"post",url:"/ltspc/pay/unPay/unifiedPay",data:r.Z.unFilterTokenData(e)})}async function b(e){return(0,o.Z)({method:"post",url:"/ltspc/pay/rpc/cardRouteInfo/get",data:e})}async function w(e){return(0,o.Z)({method:"post",url:"/ltspc/pay/unPay/complete",data:e})}function S({currency:e}){return(0,a.Z)({url:"/api/ocp/appCurrency/update",params:{currency:e}})}async function T(e){return(0,o.Z)({method:"post",url:"/api/checkout/installmentDetail/get",data:e}).catch((()=>{}))}async function E(e){return(0,o.Z)({method:"get",url:"/api/ocp/addDefault/get",data:e}).catch((()=>{}))}},853943:function(e,n,t){t.d(n,{B:function(){return a}});var o=t(149500);function a(...e){var n;if("production"!==(null===(n=gbCommonInfo)||void 0===n?void 0:n.NODE_SERVER_ENV))try{o.info("%cOneClickPay:","background:#35495e ; padding: 3px 3px; border-radius: 3px;  color: #fff",...e,"\n")}catch(n){o.log(...e)}}},511801:function(e,n,t){var o=t(710311),a=t(31530),i=t(573543),l=t(859104),s=t(291982),r=t(370687),c=t(76009);const{rpcCardSelect:u,rpcTokenInfo:d}=(0,c.Z)(),{paypalGaVault:p,ppgvAccountInfo:h}=(0,r.Z)(),f=new Map;n.Z=({abtInfo:e,paymentMethods:n,payParams:t,calculateMallInfo:r}={})=>{let c=!1;const v=(0,o.computed)((()=>i.Z.getPayInlineAbtInfo({abtInfo:e.value}))),g=(0,o.computed)((()=>i.Z.getNeedPayInlineMethods({payInlineAbtInfo:v.value,paymentMethods:n.value}))),y=(0,o.ref)({});return{payInlineAbtInfo:v,needPayInlineMethods:g,installmentInfo:y,fetchInstallmentInfo:async function(e={}){var n,o,a,i,s,u;if(e=Object.assign({},{country:null==t||null===(n=t.value)||void 0===n?void 0:n.country_code,amount:null==r||null===(o=r.value)||void 0===o||null===(a=o.grandTotalPrice)||void 0===a?void 0:a.amount,currency:null==t||null===(i=t.value)||void 0===i?void 0:i.currency},e),c)return;c=!0;const{country:d,amount:p,currency:h}=e||{},v=`ocp_${d}_${p}_${h}`;if(f.has(v))return c=!1,y.value=f.get(v),y.value;const g=await(0,l.c9)({country:d,amount:p,currency:h});if(c=!1,null!=g&&null!==(s=g.info)&&void 0!==s&&null!==(u=s.front_show_list)&&void 0!==u&&u.length){const e={};g.info.front_show_list.forEach((n=>{e[n.payment_code]=n})),g.info.front_list_items=e,y.value=g.info||{}}return 0==(null==g?void 0:g.code)&&null!=g&&g.info&&f.set(v,(null==g?void 0:g.info)||{}),y.value},ocpAnalysis:async({other:e={},daId:n="",detailPopupOptions:t={}})=>{var o,i;let l={default_payment_code:null==t?void 0:t.defaultPaymentInfo,is_signed:"-",is_token:"-",activity_from:"",location:t.location||"popup",...e};var r,c;"routepay-card"===(null==t?void 0:t.defaultPaymentInfo)&&(l.is_token="1"==(null==u?void 0:u.type)&&null!==(r=d.value)&&void 0!==r&&r.id?1:0);"PayPal-GApaypal"===(null==t?void 0:t.defaultPaymentInfo)&&(l.is_signed="1"==p.type&&null!==(c=h.value)&&void 0!==c&&c.sign_up_email?1:0);const{src_module:f}=(0,a.d)(location.search||"");(["page_payment_successful","page_order_detail"].includes(null===(o=SaPageInfo)||void 0===o?void 0:o.page_name)||"page_goods_detail"===(null===(i=SaPageInfo)||void 0===i?void 0:i.page_name)&&["page_payment_successful_page","page_order_detail_page"].includes(f))&&(l.activity_from="one_tap_pay"),s.S.triggerNotice({daId:n,extraData:l})}}}},370687:function(e,n,t){var o=t(710311),a=t(573543),i=t(287272);const l=(0,o.reactive)({valid:!1,visibleArrow:!1,radioType:i.u.CHECKED,type:i.u.CHECKED,express:!1}),s=(0,o.ref)({});n.Z=()=>({paypalGaVault:l,ppgvAccountInfo:s,handleInitPaypalVaulting:({paymentMethods:e,abtInfo:n})=>{var t;if(null===(t=e.value)||void 0===t||!t.length)return;s.value=a.Z.getPpgvAccountInfo({paymentMethods:e.value})||{};const o=a.Z.initPaypalVaultingConfig({abtInfo:n.value,ppgvAccountInfo:s.value});Object.assign(l,o)},resetState:()=>{l.valid=!1,l.visibleArrow=!1,l.radioType=i.u.CHECKED,l.type=i.u.CHECKED,s.value={}}})},76009:function(e,n,t){var o=t(710311),a=t(573543);const i=(0,o.reactive)({valid:!1,type:"1"}),l=(0,o.ref)({}),s=(0,o.ref)({});n.Z=()=>({rpcCardSelect:i,rpcTokenInfo:l,rpcPayment:s,handleInitRpcToken:({paymentMethods:e})=>{var n,t,o;null!==(n=e.value)&&void 0!==n&&n.length&&(s.value=a.Z.getRpcPayment({paymentMethods:e.value})||{},l.value=a.Z.getRpcCardToken({rpcPayment:s.value})||{},i.valid=!(null===(t=l.value)||void 0===t||!t.id),i.type=null!==(o=l.value)&&void 0!==o&&o.id?"1":"0")},resetState:()=>{i.valid=!1,i.type="1",l.value={},s.value={}}})},609065:function(e,n,t){t.d(n,{Z:function(){return k}});var o=t(343957),a=t(382358),i=t(35847),l=t(165318),s=t.n(l),r=t(465163),c=t(31530),u=t(16356),d=t(853943),p=t(828665),h=t(843326),f=t(710311),v=t(902176),g=t(512247),y=t(291982),m=t(859104),_=t(573543),P=t(770457),C=t(511801),I=t(149500);const{ocpAnalysis:b}=(0,C.Z)();y.S.addSubscriber({modulecode:"2-23"});const{langPath:w}=gbCommonInfo,S=()=>{};var T=new WeakMap;class E{constructor(){var e,n;((0,a.Z)(this,"options",{scene:"",location:""}),(0,a.Z)(this,"openOneClickPayCompliance",!0),(0,a.Z)(this,"initPciSuccess",!1),(0,a.Z)(this,"payParams",{good_sn:"",goods_id:0,sku_code:"",business_model:0,mall_code:"",payment_code:"",billno:"",sign_up_email:"",show_card_no:"",quantity:0,amount:0,currency:"",country_code:"",payment_no:""}),(0,a.Z)(this,"onSuccess",S),(0,a.Z)(this,"onFail",S),(0,a.Z)(this,"onComplete",S),(0,a.Z)(this,"containerInstance",null),(0,a.Z)(this,"language",{}),(0,o.Z)(this,T,{writable:!0,value:{PaymentFailed:["302457","302461","302456","302460"]}}),"undefined"!=typeof window)&&(this.cbPayResult(),this.initPay(),this.forterLoad(),this.initRiskifiedDeviceId(),this.getLanguage(),this.getApolloConfig(),this.containerInstance&&(null===(e=(n=this.containerInstance).teardown)||void 0===e||e.call(n)))}get hiddenSuccessToast(){var e;return"ProductDetail"===(null===(e=this.options)||void 0===e?void 0:e.scene)}async initPay(e={checkLogin:!1}){if(this.initPciSuccess)return!0;const n=await(0,m.gY)();return(0,d.B)("initPay---",n),300206==(null==n?void 0:n.code)&&e.checkLogin&&(this.closeAllToast(),SHEIN_LOGIN&&SHEIN_LOGIN.show({show:!0,cb:S})),this.initPciSuccess=0==(null==n?void 0:n.code),n}initCybersource(){var e,n,t;"undefined"!=typeof window&&(null!==(e=window)&&void 0!==e&&e.GB_cybersource_uuid||null===(n=window)||void 0===n||null===(t=n.TPM)||void 0===t||t.run({marketing:"Cybersource",method:"_loadCybersource"}))}forterLoad(){var e,n,t;"undefined"!=typeof window&&(null===(e=window)||void 0===e||null===(n=e.TPM)||void 0===n||n.run({marketing:"Forter",method:"_loadFort"}),null===(t=document)||void 0===t||t.addEventListener("ftr:tokenReady",(function(e){window.forterDeviceId=e.detail||window.forterDeviceId||""})))}initRiskifiedDeviceId(){var e;if("undefined"==typeof window)return;let n=null===(e=window.TPM)||void 0===e?void 0:e.runImmediate({marketing:"Riskified",method:"_getRiskSessionId"});n&&(window.riskifiedDeviceId=(null==n?void 0:n.datas)||"")}async create(e={params:{good_sn:"",goods_id:0,sku_code:"",business_model:0,mall_code:"",payment_code:"",billno:"",sign_up_email:"",show_card_no:"",quantity:0,amount:0,currency:"",country_code:"",goods_thumb:"",payment_no:""},options:{scene:"",location:""},onSuccess:S,onFail:S,onComplete:S}){var n,o;if(this.onSuccess=(null==e?void 0:e.onSuccess)||S,this.onFail=(null==e?void 0:e.onFail)||S,this.onComplete=(null==e?void 0:e.onComplete)||S,this.payParams=(null==e?void 0:e.params)||{},this.payParams.currency=(0,u.e)("currency")||gbCommonInfo.default_currency||(null===(n=gbCommonInfo)||void 0===n?void 0:n.currency),this.options=(null==e?void 0:e.options)||{},this.detailPopupOptions={},(0,d.B)("payParams---",this.payParams),(0,d.B)("options---",this.options),this.showProcessToast(),this.clientApiReport({apiPath:"one-click-pay/ocp-create",billno:null===(o=this.payParams)||void 0===o?void 0:o.billno}),!this.containerInstance&&(this.containerInstance=await(async()=>{const e=await Promise.all([t.e(24215),t.e(47594),t.e(50059),t.e(89812)]).then(t.bind(t,360495)).catch((()=>""));if(null==e||!e.default)return;const n=(0,f.createApp)(null==e?void 0:e.default);n.use(v.Z);const o=document.createElement("div");o.setAttribute("class","oneclick-pay-container"),document.body.appendChild(o);const a=n.mount(o);return a.unmount=()=>{a.unmount(),document.body.removeChild(o)},a})(),!this.containerInstance))return this.reportClientError({apiPath:"one-click-pay/init-container-error"}),this.onComplete({type:"cancel",extraData:{}}),void this.closeAllToast();const a=await this.initPay({checkLogin:!0});if(!this.initPciSuccess)return this.reportClientError({apiPath:"one-click-pay/init-pci-error",err:a}),this.onComplete({type:"cancel",extraData:{}}),void this.closeAllToast();this.initCybersource(),this.containerInstance.$nextTick((async()=>{this.showProcessToast(),this.containerInstance.updateLanguage(this.language);const e=await(0,m.h5)(this.payParams);var n,t,o,a;(this.detailPopupOptions=e,e.isCalculateMallSuccess)?(this.preLoadChannelSdk({abtInfo:e.abtInfo,paymentMethods:e.paymentMethods}),(0,d.B)("detailPopupOptions---",e),this.closeAllToast(),this.containerInstance.openOCP({options:{maxCount:E.MERGE_COUNT_LIMIT,payParams:this.payParams,otherOptions:this.options,...e||{}},onClose:()=>{this.onComplete({type:"cancel",extraData:{}})},onPayComplete:this.handlePayComplete.bind(this),showProcessToast:this.showProcessToast.bind(this),closeAllToast:this.closeAllToast.bind(this)}),b({daId:"2-23-7",detailPopupOptions:{defaultPaymentInfo:null==e||null===(n=e.defaultPaymentInfo)||void 0===n?void 0:n.code,location:null===(t=this.options)||void 0===t?void 0:t.location}})):(this._handleFail({limitMerge:!1,status:"Fail",scene:"RearEnd",response:e.calculateMall}),this.clientApiReport({apiPath:"one-click-pay/cal-fail",billno:null===(o=this.payParams)||void 0===o?void 0:o.billno,errorCode:null==e||null===(a=e.calculateMall)||void 0===a?void 0:a.code}))}))}getLanguage(){return(0,h.Z)({url:"/api/common/language/get",params:{page:"one_click_pay"}}).then((({language:e})=>(this.language=e,this.containerInstance&&(this.containerInstance.language=this.language),e)))}async getApolloConfig(){const e=await(0,h.Z)({method:"POST",url:"/api/config/apollo/get",data:{apolloKeys:"ONE_CLICK_PAY_MAXIMUM_NUMBER_OF_TIMES"}}),{ONE_CLICK_PAY_MAXIMUM_NUMBER_OF_TIMES:n=0}=(null==e?void 0:e.info)||{};E.MERGE_COUNT_LIMIT=Number(n||5)}showCustomizeToast({type:e="",htmlString:n="",duration:t=3e3,onClose:o=(()=>({}))}={}){this.closeAllToast(),(0,p.a)({duration:t||3e3,type:e,htmlString:n,onClose:o})}showFailToast({errorMsg:e="",extraData:n={}}){this.closeAllToast(),(0,p.a)({duration:3e3,type:"error",htmlString:e,onClose:()=>{this.onComplete({type:"fail",extraData:n})}})}showSuccessToast(){return new Promise((e=>{var n;let t=!1;this.closeAllToast(),(0,p.a)({duration:3e3,type:"success",message:null===(n=this.language)||void 0===n?void 0:n.SHEIN_KEY_PC_15473,onClose(){t=!0,setTimeout(e,100)}}),setTimeout((()=>{t||e()}),3800)}))}showProcessToast(){var e;this.closeAllToast(),(0,p.a)({duration:0,type:"loading",loadingColor:"#2D68A8",message:`${(null===(e=this.language)||void 0===e?void 0:e.SHEIN_KEY_PC_15031)||""}...`})}closeAllToast(){null===p.a||void 0===p.a||p.a.closeAll()}reportClientError(e){const{apiPath:n,...t}=e||{},{payment_code:o="",billno:a=""}=this.payParams||{};(0,d.B)("reportClientError---",n,t),g.Z.report({apiPath:n,billno:a,nowPageType:"oneClickPay",payment_method:o,err:JSON.stringify((null==t?void 0:t.err)||{})})}clientApiReport(e){var n;const{apiPath:t,...o}=e||{},{billno:a=""}=this.payParams||{};null===g.Z||void 0===g.Z||null===(n=g.Z.report)||void 0===n||n.call(g.Z,{apiPath:t,billno:a,nowPageType:"ocp",...o})}_handleCustomize({status:e,response:n}){var t;((0,d.B)("customize--response---",n),"pending"===e)&&(this.showCustomizeToast({type:"success",htmlString:null===(t=this.language)||void 0===t?void 0:t.SHEIN_KEY_PC_24437,onClose:()=>({})}),this.onComplete({type:"cancel",extraData:{}}))}_handleSuccess(e){var n,t,o,a,i,l,s;const c=Object.assign({},{limitMerge:!1},e);if(!this.hiddenSuccessToast){var u,d,p;const[e]=(null==c||null===(u=c.response)||void 0===u||null===(d=u.info)||void 0===d||null===(p=d.order)||void 0===p?void 0:p.order_group)||[];y.S.triggerNotice({daId:"2-23-1",extraData:{order_no:this.payParams.billno,merge_count:(null==e?void 0:e.merge_count)||0,style:"toast"}})}b({daId:"2-23-12",detailPopupOptions:{defaultPaymentInfo:null===(n=this.detailPopupOptions)||void 0===n||null===(t=n.defaultPaymentInfo)||void 0===t?void 0:t.code,location:null===(o=this.options)||void 0===o?void 0:o.location},other:{activity_from:"one_tap_pay",result:1,result_reason:(null==e||null===(a=e.response)||void 0===a||null===(i=a.info)||void 0===i?void 0:i.error_code)||(null==e||null===(l=e.response)||void 0===l?void 0:l.code)||"0"}}),null===(s=this.onSuccess)||void 0===s||s.call(this,c),!c.limitMerge||this.hiddenSuccessToast?Promise.all([(0,m.A9)(this.payParams),this.hiddenSuccessToast?void 0:this.showSuccessToast()]).then((([e])=>{var n;null===(n=this.onComplete)||void 0===n||n.call(this,{type:"success",extraData:{result:c,addPurchaseRelation:e}})})).catch((e=>{var n;null===(n=this.onComplete)||void 0===n||n.call(this,{type:"success",extraData:{result:c,addPurchaseRelation:{}}}),this.reportClientError({apiPath:"one-click-pay/get-purchase-relation-error",err:e})})):(this.closeAllToast(),this.containerInstance.showResultTips({type:"success",title:this.language.SHEIN_KEY_PC_15473,description:(0,r.X)(E.MERGE_COUNT_LIMIT,this.language.SHEIN_KEY_PC_25007),onClose:()=>{(0,m.A9)(this.payParams).then((e=>{var n;null===(n=this.onComplete)||void 0===n||n.call(this,{type:"success",extraData:{result:c,addPurchaseRelation:e}})}))},onSubmit:()=>{(0,m.A9)(this.payParams).then((e=>{var n;null===(n=this.onComplete)||void 0===n||n.call(this,{type:"success",extraData:{result:c,addPurchaseRelation:e}})})),this.containerInstance.hideResultTips()},onCancel:()=>{this.containerInstance.hideResultTips(),location.href=`${w}/user/orders/list`}}))}_handleFail(e){var n,t,o,a,i,l,s;const{response:r}=e||{},c=Object.assign({},{limitMerge:!1},e);if((0,d.B)("_handleFail---",c),null===(n=this.onFail)||void 0===n||n.call(this,c),this.closeAllToast(),c.limitMerge)this.containerInstance.showResultTips({type:"fail",title:this.language.SHEIN_KEY_PC_25014,description:(null==r?void 0:r.tips)||this.language.SHEIN_KEY_PC_27106,onSubmit:()=>{var e;null===(e=this.onComplete)||void 0===e||e.call(this,{type:"fail",extraData:{result:c}})},onClose:()=>{var e;null===(e=this.onComplete)||void 0===e||e.call(this,{type:"fail",extraData:{result:c}})}});else{const{info:e,tips:n,code:t="",msg:o=""}=r||{};let a=(null==e?void 0:e.show_error_msg)||n||o||this.language.SHEIN_KEY_PC_27106||this.language[302459];"302202"==t?a=(null==e?void 0:e.msg)||this.language.SHEIN_KEY_PC_27106||this.language[302459]:302207==t&&(a=(n||"").replace("{0}",e||"")),0==t&&"OK"==o&&(a=this.language.SHEIN_KEY_PC_27106||this.language[302459]),this.showFailToast({errorMsg:a,extraData:{result:c}})}y.S.triggerNotice({daId:"2-23-6",extraData:{style:c.limitMerge?"popup":"toast",reason_tp:(null==e||null===(t=e.response)||void 0===t||null===(o=t.info)||void 0===o?void 0:o.error_code)||(null==e||null===(a=e.response)||void 0===a?void 0:a.code)||"0"}}),b({daId:"2-23-12",detailPopupOptions:{defaultPaymentInfo:null===(i=this.detailPopupOptions)||void 0===i||null===(l=i.defaultPaymentInfo)||void 0===l?void 0:l.code,location:null===(s=this.options)||void 0===s?void 0:s.location},other:{activity_from:"one_tap_pay",result:2,result_reason:""}})}handlePayComplete({status:e,response:n,failScene:t=""}){(0,d.B)("handlePayComplete-----",{status:e,response:n}),this.closeAllToast();let o="",a="";if("success"===e)this._handleSuccess({limitMerge:!1,status:"Success",scene:"RearEnd",response:{code:0}});else if("fail"===e){var l,s;const e=(0,i.Z)(this,T).PaymentFailed.includes(`${null==n?void 0:n.code}`);o=(null==n||null===(l=n.info)||void 0===l?void 0:l.show_error_msg)||(null==n?void 0:n.tips)||(null==n?void 0:n.msg),a=(null==n||null===(s=n.info)||void 0===s?void 0:s.error_code)||(null==n?void 0:n.code),this._handleFail({limitMerge:e,status:"Fail",scene:"RearEnd",response:n,failScene:t})}else"cancel"===e?this.onComplete({type:"cancel",extraData:{}}):this._handleCustomize({status:e,response:n});this.clientApiReport({apiPath:"one-click-pay/pay-complete",status:e,failScene:t,errorMessage:o,errorCode:a})}async cbPayResult(){if("undefined"==typeof window)return;const e=_.Z.getCache();if(Object.keys(e||{}).length){const{isOcp:i}=(0,c.d)(location.search||"");if(i){await this.getLanguage().catch((()=>{}));const{action:n,result:t,pending:o}=(null==e?void 0:e.info)||{},{}=e||{};let a="fail";0==(null==e?void 0:e.code)&&"direct"==n&&1==t&&(a="success"),"direct"==n&&0==t&&1==o&&(a="pending"),this.handlePayComplete({status:a,response:e})}_.Z.clearCache();try{var n,t,o,a;const e=new(s())(location.href);null==e||null===(n=e.searchParams)||void 0===n||null===(t=n.delete)||void 0===t||t.call(n,"isOcp"),null===(o=window.history)||void 0===o||null===(a=o.replaceState)||void 0===a||a.call(o,null,"",e.href)}catch(e){I.error(e)}}}preLoadChannelSdk({abtInfo:e,paymentMethods:n}){var t;if("undefined"!=typeof window){var o,a,i,l,s;if(null!=n&&n.find((e=>"PayPal-GApaypal"===e.code)))null===(o=window.TPM)||void 0===o||o.run({marketing:"InitChannelSdk",method:"_initPPGASdk"});if(null!=n&&n.find((e=>"PayPal-Venmo"===e.code)))null===(a=window.TPM)||void 0===a||a.run({marketing:"InitChannelSdk",method:"_initVenmoSdk"});if(null!=n&&n.find((e=>P.w.includes(e.code))))null===(i=window.TPM)||void 0===i||i.run({marketing:"InitChannelSdk",method:"_initGPSdk"});if(null!==(t=e.PayInline)&&void 0!==t&&t.p.indexOf("klarna="))null===(l=window.TPM)||void 0===l||l.run({marketing:"InitChannelSdk",method:"_initKlarnaSdk"});if(null!=n&&n.find((e=>"afterpay-cashapp"===e.code)))null===(s=window.TPM)||void 0===s||s.run({marketing:"InitChannelSdk",method:"_initAfterpaySdk"})}}}(0,a.Z)(E,"MERGE_COUNT_LIMIT",5);var k=new E},573543:function(e,n,t){var o=t(382358),a=t(31530),i=t(174865),l=t(244945),s=t(287272);const r=["PayPal-Venmo","PayPal-GApaypal"];class c{constructor(e){(0,o.Z)(this,"checkoutInfo",{}),(0,o.Z)(this,"clientInstanceCache",new Map),this.checkoutInfo=e||{}}updateInfo(e){Object.assign(this.checkoutInfo,e||{})}static getPpgvAccountInfo({paymentMethods:e=[]}){const n=((0,i.k)(e)?e:[]).find((e=>"PayPal-GApaypal"===e.code));return null==n?void 0:n.payment_sign_up}static getPayInlineAbtInfo({abtInfo:e={}}){const{PayInline:n}=e||{};let t={};if(null!=n&&n.p){n.p.split("&").forEach((e=>{let n=e.split("=");n[1]&&(t[n[0]]=n[1].split(","))}))}return t.paypal?t.paypal=[...new Set([...t.paypal,...r])]:t.paypal=r,t}static getRpcPayment({paymentMethods:e=[]}){return((0,i.k)(e)?e:[]).find((e=>"routepay-card"===e.code))||null}static getRpcCardToken({paymentMethods:e=[],rpcPayment:n}){const t=n||c.getRpcPayment({paymentMethods:e});return null==t?void 0:t.card_token}static getDefaultPayment({paymentMethods:e=[]}){const n=(0,i.k)(e)?e:[],t=n.filter((e=>1==e.enabled));return t.length?t.find((e=>e.default_payment))||t[0]:n.find((e=>e.default_payment))||n[0]}static getNeedPayInlineMethods({payInlineAbtInfo:e,paymentMethods:n=[],abtInfo:t={}}){var o;e=null!==(o=e)&&void 0!==o?o:c.getPayInlineAbtInfo({abtInfo:t});const a=Object.values(e||{}).reduce(((e,n)=>[...e,...n]),[]);return(null==n?void 0:n.filter((e=>a.indexOf(e.code)>-1)).map((e=>e.code)))||[]}static initPaypalVaultingConfig({abtInfo:e={},ppgvAccountInfo:n={}}){var t;const o=(0,a.d)((null==e||null===(t=e.PayPalVaultingnew)||void 0===t?void 0:t.p)||""),i={valid:!1,visibleArrow:!1,radioType:s.u.CHECKED,type:s.u.CHECKED,express:!1};return"open"===(null==o?void 0:o.signed)&&"open"===(null==o?void 0:o.unsigned)&&(i.valid=i.visibleArrow=!0),"open"===(null==o?void 0:o.signed)&&"open"!==(null==o?void 0:o.unsigned)&&(i.visibleArrow=i.valid=!(null==n||!n.sign_up_email)),"open"!==(null==o?void 0:o.signed)&&"open"===(null==o?void 0:o.unsigned)&&(i.valid=i.visibleArrow=!(null!=n&&n.sign_up_email)),"open"!==(null==o?void 0:o.signed)&&"open"!==(null==o?void 0:o.unsigned)&&(i.valid=i.visibleArrow=!1),i.express=!1,i.valid&&i.visibleArrow&&"open"===(null==o?void 0:o.unsigned)&&(null!=n&&n.sign_up_email||(i.express=!0,i.visibleArrow=!1)),i.valid||(i.radioType=s.u.UNCHECKED,i.type=s.u.UNCHECKED),i}static async getPayPalClientInstance({clientToken:e}){if("undefined"==typeof window)return Promise.reject("window is undefined");try{return await window.braintree.client.create({authorization:e}).catch((e=>{throw e}))}catch(e){return Promise.reject(e)}}static async getPaypalDeviceData(e){if(!e)return Promise.reject("clientInstance is required");const n=await window.braintree.dataCollector.create({client:e}).catch((e=>{throw e}));return(null==n?void 0:n.deviceData)||""}static checkTokenCvv({card_type:e,cvv:n}){var t,o;const a=(null===(t=e||"")||void 0===t||null===(o=t.toUpperCase)||void 0===o?void 0:o.call(t))||"";let i=/^\d{3,4}$/;const l={AMEX:/^\d{3,4}$/,MAESTRO:/^(\d{3}|\s{0})$/,other:/^\d{3}$/};return a&&(i=l[a]||l.other),i.test(n)}static getCvvMaxLen({card_type:e}){var n,t;const o=(null===(n=e||"")||void 0===n||null===(t=n.toUpperCase)||void 0===t?void 0:t.call(n))||"",a={AMEX:4,MAESTRO:3,other:3};return o?a[o]||a.other:4}static getDeviceInfo(){return{colorDepth:window.screen.colorDepth,javaEnabled:window.navigator.javaEnabled()?1:0,language:window.navigator.language,screenHeight:window.screen.height||0,screenWidth:window.screen.width||0,timeZoneOffset:(new Date).getTimezoneOffset(),userAgent:window.navigator.userAgent,origin:window.location.origin}}static unFilterTokenData(e){const{langPath:n,host:t}=gbCommonInfo,o=t+n+"/ltspc/pay/result/unifiedCb?bill_no="+e.billno+`&isOcp=1&ocpCbUrl=${encodeURIComponent(location.href)}`,a=o,i=o,s=(0,l.C)({url:o,mergeObj:{ocpCancel:1}});return{billno:e.billno||"",publicKeyId:e.publicKeyId||"",sessionId:e.sessionId||"",tokenId:e.tokenId||"",cardBin:e.cardBin||"",cardLastFour:e.cardLastFour||"",deviceFingerId:e.deviceFingerId||"",challengeWindowSize:e.challengeWindowSize||"",forterDeviceFingerprintID:e.forterDeviceFingerprintID||"",riskifiedDeviceFingerprintID:e.riskifiedDeviceFingerprintID||"",paymentCode:e.paymentCode||"",cvvHash:e.cvvHash||"",cvvCheck:e.cvvCheck||"",GB_cybs_loaded:1,callbackUrl:o,cancelUrl:s,pendingUrl:i,failureUrl:a,device_language:navigator.language||navigator.browserLanguage||"",checkoutScene:"ocp",...c.getDeviceInfo()}}static getCache(){const{SiteUID:e}=gbCommonInfo||{},n=`${e}_OCP_CACHE`,t=localStorage.getItem(n);try{return t?JSON.parse(t):{}}catch(e){return{}}}static setCache(e={}){const{SiteUID:n}=gbCommonInfo||{},t=`${n}_OCP_CACHE`;sessionStorage.setItem(t,JSON.stringify(e||{}))}static clearCache(){const{SiteUID:e}=gbCommonInfo||{},n=`${e}_OCP_CACHE`;localStorage.removeItem(n)}static isPendingStatus(e){const{action:n,result:t,pending:o}=(null==e?void 0:e.info)||{};return"direct"===n&&0==t&&1==o}}n.Z=c},902176:function(e,n,t){t.d(n,{Z:function(){return l},o:function(){return s}});var o=t(710311),a=t(853943);const i=Symbol("store");function l(e){const n=(e={})=>({priceSummary:{},goodsInfo:{},paymentMethods:[],currencySupportList:[],payParams:{},abtInfo:{},language:{},defaultPaymentInfo:{},selectPaymentInfo:{},prePaymentDefaultCurrency:"",preSelectPaymentInfo:{},visibleGlobalLoading:!1,preCalculateMallInfo:{},calculateMallInfo:{},cccTaxIncludedTips:"",...e}),t=(0,o.reactive)(n());const l=(0,o.computed)((()=>t.paymentMethods));e.provide(i,(0,o.readonly)({state:t,sortedPayments:l,assignState:function(e){(0,a.B)("assignState---",e),Object.assign(t,e)},updateLoadingStatus:function({loading:e=!1}){t.visibleGlobalLoading=!!e},updatePaymentMethods:function(e){t.paymentMethods=e},updateLanguage:function(e){t.language=Object.assign({},t.language,e)},resetStore:function(){Object.assign(t,n({language:t.language,visibleGlobalLoading:t.visibleGlobalLoading}))}}))}function s(){return(0,o.inject)(i)}},995692:function(e,n,t){t.d(n,{Z:function(){return b}});var o=t(154815),a=t(307282),i=t.n(a),l=t(400391),s=t.n(l),r=t(276015),c=t.n(r),u=t(914532),d=t.n(u),p=t(40174),h=t.n(p),f=t(552119),v=t.n(f),g=t(613167),y=t.n(g);function m(e,n){return e.data=d().call(n,e.data,e.headers,n.transformResponse),e}function _(e,n,t,o,a,l=!1){const s=[...e];for(;s.length;){const{fulfilled:e,rejected:r}=s[n](),c=l?r:e;if(c){try{a=c===m?c(a,t):c(a),l=!1}catch(e){a=e,l=!0}if("[object Promise]"===Object.prototype.toString.call(a)){a=new(i())("Promise is unsupported in synchronizeInstance","INTERCEPTOR_ILLEGAL",t,o,a),l=!0;break}}}return{payload:a,inCatch:l}}function P(e,n,t,o,a){const{success:i,error:l,complete:s}=n;if("function"==typeof i||"function"==typeof l){const{payload:s,inCatch:r}=_(e.interceptors.response.handlers,"shift",n,t,o,a);r?"function"==typeof l&&l(s):"function"==typeof i&&i(s)}"function"==typeof s&&s(t)}function C(e){return e.defaults.adapter=function(e){return async function(n){try{(n=>{const{payload:t,inCatch:o}=_(e.interceptors.request.handlers,"pop",n,null,n);if(o)return void P(e,n,null,t,!0);const a=(n=t).headers,l=n.responseType;let r=n.data;s().isFormData(r)&&s().isStandardBrowserEnv()&&delete a["Content-Type"];let u=new XMLHttpRequest;const d=c()(n.baseURL,n.url);u.open(n.method.toUpperCase(),h()(d,n.params,n.paramsSerializer),!1),"setRequestHeader"in u&&s().forEach(a,(function(e,n){void 0===r&&"content-type"===n.toLowerCase()?delete a[n]:u.setRequestHeader(n,e)})),s().isUndefined(n.withCredentials)||(u.withCredentials=!!n.withCredentials),l&&"json"!==l&&(u.responseType=n.responseType),r||(r=null);const p=y()(d);p&&-1===["http","https","file"].indexOf(p)?P(e,n,u,new(i())("Unsupported protocol "+p+":",i().ERR_BAD_REQUEST,n),!0):(u.send(r),function(){if(!u||4!==u.readyState)return;if(0===u.status&&(!u.responseURL||0!==u.responseURL.indexOf("file:")))return;const t="getAllResponseHeaders"in u?v()(u.getAllResponseHeaders()):null,o={data:l&&"text"!==l&&"json"!==l?u.response:u.responseText,status:u.status,statusText:u.statusText,headers:t,config:n,request:u};u.status>=200&&u.status<300||304===u.status?P(e,n,u,o):P(e,n,u,new(i())("Network Error",i().ERR_NETWORK,n,u,o),!0),u=null}())})(n)}catch(e){return{code:"-1",msg:"Unexpected Error in SyncXhrAdapter",error:e}}return{code:"0",msg:"SyncXhrAdapter Work Expectant"}}}(e),e.interceptors.request.forEach=e.interceptors.response.forEach=function(){},e.interceptors.response.handlers.unshift({fulfilled:m}),e}var I=t(203510);var b=(()=>{if("undefined"==typeof window)return o.Z;const e=C(o.Z.create({baseURL:gbCommonInfo.langPath,headers:{common:{"x-requested-with":"XMLHttpRequest"}},params:{_ver:"1.1.8",_lang:gbCommonInfo.appLanguage}}));return e.interceptors.request.use((e=>(e.headers["x-csrf-token"]=gbCommonInfo.csrf_token,e))),e.interceptors.request.use(I.QD),e.interceptors.response.use(I.Cw),e.interceptors.response.use(I.t$.success,I.t$.error),e.interceptors.response.use((e=>{var n;return null!==(n=e.config.schttp)&&void 0!==n&&n.getResponse?e:e.data}),(e=>{var n,t;throw null!==(n=e.config)&&void 0!==n&&null!==(t=n.schttp)&&void 0!==t&&t.getResponse||!e.request?e:e.request})),window.schttpSync=e,e})()},457204:function(e,n,t){t.d(n,{B:function(){return i}});var o=t(843326),a=t(995692);function i({isAsync:e,opt:n,successCb:t,errorCb:i,completeCb:l}){e?(0,o.Z)({...n}).then((e=>{t&&t(e)})).catch((e=>{i&&i(e)})).finally((e=>{l&&l(e)})):(0,a.Z)({...n,success:e=>{t&&t(e)},error:e=>{i&&i(e)},complete:e=>{l&&l(e)}})}}}]);