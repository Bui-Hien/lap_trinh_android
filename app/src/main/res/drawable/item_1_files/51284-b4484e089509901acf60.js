"use strict";(self.webpackChunkSHEIN_W=self.webpackChunkSHEIN_W||[]).push([[51284],{751284:function(e,t,r){var n=r(501524),i=r.n(n),o=r(281883),s=r(515033),l=r(662465),d=r(805726),_=r.n(d),a=r(396548),u=r(149500);const{lang:c,SERVER_TYPE:p,COUNTRY_PAYMENT_METHOD_CONFIG:E}=gbCommonInfo,g="blue",m="red",h="green",C="grey";t.Z=new class{orderStatus(e={}){return{0:e.SHEIN_KEY_PC_15032,1:e.SHEIN_KEY_PC_15090,2:e.SHEIN_KEY_PC_15099,3:e.SHEIN_KEY_PC_15028,4:e.SHEIN_KEY_PC_15031,5:e.SHEIN_KEY_PC_15190,6:e.SHEIN_KEY_PC_15280,7:e.SHEIN_KEY_PC_15190,8:e.SHEIN_KEY_PC_15305,9:e.SHEIN_KEY_PC_15307,10:e.SHEIN_KEY_PC_15026,11:e.SHEIN_KEY_PC_15073,12:e.SHEIN_KEY_PC_15032,13:e.SHEIN_KEY_PC_15279,14:e.SHEIN_KEY_PC_15324,15:e.SHEIN_KEY_PC_15324,16:e.SHEIN_KEY_PC_15213,17:e.SHEIN_KEY_PC_15089,18:e.SHEIN_KEY_PC_15026,19:e.SHEIN_KEY_PC_14485}||{}}orderStatusColor(){return{0:g,1:g,2:C,3:C,4:g,5:h,6:g,7:h,8:C,9:C,10:g,11:h,12:g,13:g,14:g,15:g,16:m,17:g,18:g,19:g}||{}}orderGoodsStatus(e={}){return{0:e.SHEIN_KEY_PC_15025,1:e.SHEIN_KEY_PC_15090,2:e.SHEIN_KEY_PC_15031,3:e.SHEIN_KEY_PC_15027,4:e.SHEIN_KEY_PC_15089,5:e.SHEIN_KEY_PC_15073,6:e.SHEIN_KEY_PC_15070,7:e.SHEIN_KEY_PC_15028,8:e.SHEIN_KEY_PC_15099,9:e.SHEIN_KEY_PC_15071,10:e.SHEIN_KEY_PC_15070,11:e.SHEIN_KEY_PC_15190,12:e.SHEIN_KEY_PC_15305,13:e.SHEIN_KEY_PC_15307,14:e.SHEIN_KEY_PC_15324,15:e.SHEIN_KEY_PC_15325,16:e.SHEIN_KEY_PC_15213,17:"",18:e.SHEIN_KEY_PC_15027,19:e.SHEIN_KEY_PC_15963}||{}}giftOrderStatus(e={}){return{1:e.SHEIN_KEY_PC_15032,2:e.SHEIN_KEY_PC_15028,3:e.SHEIN_KEY_PC_15090,4:e.SHEIN_KEY_PC_15031,5:e.SHEIN_KEY_PC_15031,6:e.SHEIN_KEY_PC_17040,7:e.SHEIN_KEY_PC_15190,8:e.SHEIN_KEY_PC_15089,9:e.SHEIN_KEY_PC_15073,10:e.SHEIN_KEY_PC_15028,11:e.SHEIN_KEY_PC_15028,12:e.SHEIN_KEY_PC_15213,13:e.SHEIN_KEY_PC_15213,14:e.SHEIN_KEY_PC_15028}||{}}giftOrderStatusColor(){return{1:g,2:C,3:g,4:g,5:g,6:g,7:h,8:g,9:h,10:C,11:C,12:m,13:m,14:C}||{}}fliterStatus(e,t){return{"签收":t.SHEIN_KEY_PC_16649,"已发货":t.SHEIN_KEY_PC_15026,"运输中":t.SHEIN_KEY_PC_16647,"派送中":t.SHEIN_KEY_PC_16648,"已下单":t.SHEIN_KEY_PC_16658,"待取件":t.SHEIN_KEY_PC_16659,"包裹异常":t.SHEIN_KEY_PC_25687}[e]||""}xtraOrderCancelReason(e={}){return[{index:6,title:e.SHEIN_KEY_PC_27034},{index:7,title:e.SHEIN_KEY_PC_27035},{index:9,title:e.SHEIN_KEY_PC_27036},{index:11,title:e.SHEIN_KEY_PC_27037},{index:8,title:e.SHEIN_KEY_PC_27038},{index:12,title:e.SHEIN_KEY_PC_27039},{index:75,title:e.SHEIN_KEY_PC_27041}]}returnState(e={},t={}){const{order_status:r,shipping_status:n}=e;let i="";switch(r){case 0:i=10==n?t.SHEIN_KEY_PC_16404:t.SHEIN_KEY_PC_15031;break;case 1:i=t.SHEIN_KEY_PC_15325;break;case 2:i=t.SHEIN_KEY_PC_15355;break;case 3:i=t.SHEIN_KEY_PC_15356;break;case 4:i=t.SHEIN_KEY_PC_15149;break;case 5:case 6:i=t.SHEIN_KEY_PC_16404}return(0,s.l)({text:i})}getRefundState(e={},t={}){const{status:r,refund_record_list:n=[]}=e;let i="",o={processing:t.SHEIN_KEY_PC_15031,refunded:t.SHEIN_KEY_PC_15073,cancelled:t.SHEIN_KEY_PC_16404};if([4,5,6].indexOf(r)>-1)i="cancelled";else if([3].indexOf(r)>-1)i="refunded";else if([2].indexOf(r)>-1){i=n.filter((e=>"8"==e.status)).length?"refunded":"processing"}else i="processing";return(0,s.l)({text:o[i]})}showReturnWayOrReturnAmount(e={}){let{return_order_goods_list:t=[],isReturnOrder:r=0}=e;if(r){let e=t.filter((e=>1==e.exchange_goods_flag));return t.length==e.length}return!1}isShowRefundAmount(e={}){return"Refund-06"==e.refund_scene}refundPathTxt(e={},t={}){const{refund_path:r,refund_method:n}=e;let i="";return 1==r?i=t.SHEIN_KEY_PC_15434:2==r?i=t.SHEIN_KEY_PC_16602:3==r&&n&&(i=t.SHEIN_KEY_PC_16527),(0,s.l)({text:i})}giftOrderIsShowStatus(e){return[1,2,3,4,5,6,8,9,12,13].indexOf(e)>-1}orderCancel(e){return[0,2,12,13].indexOf(e.orderStatus)>=0}isCanRevokeByUser(e={}){const t="cod"==e.payment_method;if(![4,6].includes(e.orderStatus)||!t)return!1;const r=e.orderGoodsList||[];return!(!e.isCanRevokeByUser&&r.some((e=>Array.isArray(e.goods_sn_relation_goods_list)?e.goods_sn_relation_goods_list.some((e=>2!=e.combined_flag)):2!=e.combined_flag)))}disabledRevokeByUser(e={}){const t=e.orderGoodsList||[];return!e.isCanRevokeByUser&&t.some((e=>Array.isArray(e.goods_sn_relation_goods_list)?e.goods_sn_relation_goods_list.some((e=>2==e.combined_flag)):2==e.combined_flag))}isCommonOrderCanRefund(e={}){const t="cod"==e.payment_method;if([1,7].includes(+e.marketing_type)||![4,6].includes(e.orderStatus)||t)return!1;const r=e.orderGoodsList||[];return!(!e.isCommonOrderCanRefund&&r.every((e=>Array.isArray(e.goods_sn_relation_goods_list)?e.goods_sn_relation_goods_list.every((e=>2!=e.combined_flag)):2!=e.combined_flag)))}disabledCancelOrder(e){const t=e.orderGoodsList||[];return!e.isCommonOrderCanRefund&&t.some((e=>Array.isArray(e.goods_sn_relation_goods_list)?e.goods_sn_relation_goods_list.some((e=>2==e.combined_flag)):2==e.combined_flag))}isCommonOrderCanPartRefund(e={}){const t="cod"==e.payment_method;if([1,7].includes(+e.marketing_type)||![4,6].includes(e.orderStatus)||t)return!1;let r;return r=e&&e.preOrderGoodsList&&e.preOrderGoodsList.length?e.preOrderGoodsList:e&&e.orderGoodsList||[],!r.every((e=>!e.isCommonOrderCanPartRefund&&2!=e.combined_flag))}disabledCancelItem(e){let t;return t=e&&e.preOrderGoodsList&&e.preOrderGoodsList.length?e.preOrderGoodsList:e&&e.orderGoodsList||[],!e.isCommonOrderCanPartRefund&&t.some((e=>2==e.combined_flag))}isCodOrderCanPartRefund(e={}){const t="cod"==e.payment_method;if(![4,6].includes(e.orderStatus)||!t)return!1;let r;return r=e&&e.preOrderGoodsList&&e.preOrderGoodsList.length?e.preOrderGoodsList:e&&e.orderGoodsList||[],!(!e.isCanRevokeByUser&&r.some((e=>2!=e.combined_flag)))}disabledCodOrderPartRefund(e={}){let t;return t=e&&e.preOrderGoodsList&&e.preOrderGoodsList.length?e.preOrderGoodsList:e&&e.orderGoodsList||[],!e.isCanRevokeByUser&&t.some((e=>2==e.combined_flag))}isReturnableInfo(e,t){var r,n;let{marketing_type:i,returnable:o,voluntaryReturnType:s,returnable_Info:l}=e;var d,_;(l=null!==(r=l)&&void 0!==r?r:{},"robot"==t)&&(s=1==(null===(d=l)||void 0===d?void 0:d.voluntaryReturnType),o=null===(_=l)||void 0===_?void 0:_.returnable);return o&&s&&!(null!==(n=[1,8])&&void 0!==n&&n.includes(+i))}editAddressFlag(e={},t=[],r){const n=+e.shipping_country_id,i=+e.orderStatus,o=e.business_model,s=e.order_package_info_list||[],l="cod"===(null==e?void 0:e.payment_method);let d=!1;d=-1==t.indexOf(n)||!["in","mx"].includes(c)&&[100,137].includes(n);let _=s.filter((e=>[12,13,23].includes(+e.status))),a=[0,4].includes(+o)||"1"==o&&_.length==s.length;if(l){return([6,0,13].includes(i)||[4].includes(i)&&a)&&r?1:2}{const e=([1,0,12].includes(i)||[4].includes(i)&&a)&&!l;return e&&!d?1:!e&&2}}disabledEditAddress(e={}){let t;return t=e&&e.preOrderGoodsList&&e.preOrderGoodsList.length?e.preOrderGoodsList:e&&e.orderGoodsList||[],t.some((e=>2==e.combined_flag))}isViewInvoices(e,t){const{orderStatus:r,is_have_invoice:n}=e||{};let i=[1,3,4,5,7,10,11,14,15,16,17,18,19];return"order-list"===t&&i.push(6),1===n&&-1!==i.indexOf(r)}retainPayMethodLang(e={},t){let r;switch(t){case"adyen-konbini":r=e.SHEIN_KEY_PC_19605;break;case"dlocal-oxxo":r=e.SHEIN_KEY_PC_19609;break;case"ebanx-baloto":r=e.SHEIN_KEY_PC_19613;break;case"ebanx-oxxo":r=e.SHEIN_KEY_PC_19608;break;case"ebanx-boleto":r=e.SHEIN_KEY_PC_19611;break;default:r=e.SHEIN_KEY_PC_19607}return r}waitingForUpdateTips(e={},t,r){let n;switch(t){case"adyen-konbini":n=e.SHEIN_KEY_PC_19612;break;case"dlocal-oxxo":case"ebanx-oxxo":n=e.SHEIN_KEY_PC_19610;break;case"ebanx-baloto":n=e.SHEIN_KEY_PC_19616;break;case"ebanx-boleto":n=e.SHEIN_KEY_PC_19615;break;case"ebanx-spei":n=e.SHEIN_KEY_PC_19604;break;case"PayPal-paypal":n=r?e.SHEIN_KEY_PC_17479:e.SHEIN_KEY_PC_17396;break;default:n=e.SHEIN_KEY_PC_19606}return n}isWillShipOrder(e={}){let{payment_method:t,orderStatus:r}=e||{};return"cod"==t?[4,6].includes(+r):[1,4].includes(+r)}existFrontFlag(e={}){let{order_package_info_list:t=[]}=e||{};if(null==t||!t.length)return 0;let r=t.filter((e=>1==e.operationProcess));return null!=r&&r.length?(null==r?void 0:r.length)==(null==t?void 0:t.length)?2:1:0}isRelationBillno(e=""){return!!e&&(e.startsWith?e.startsWith("U"):0===e.indexOf("U"))}orderDateFormat(e,t=!0){return e?(e="string"==typeof e?i()(e).call(e,"-","/"):e,e=Number(e)?`${e}`.length>10?e:1e3*e:new Date(e).getTime(),(0,o.P)({time:e,sDateMap:t?l.JL:l.u8})):e}orderShortDateFormat(e){return e?(0,o.P)({time:1e3*e,defaultCode:"G-2"}):e}orderCompleteShortDateFormat(e){return e?(0,o.P)({time:1e3*e,defaultCode:"G-1"}):e}orderListGetCccTextCond(e={},t=""){let{orderStatus:r="",order_detail_status:n=""}=e||{},i=[4,6,10].includes(+r);return(null==t?void 0:t.includes("BBC=on"))&&i&&("0"==n||!!n)}orderListB2B2CCond(e={},t=""){let{orderStatus:r="",order_detail_status:n=""}=e||{},i=[4,6].includes(+r),o=null==t?void 0:t.includes("BBC=on"),s=[1,2,3].includes(+n);return o&&i&&s}orderListOrderStatusCond(e={},t=""){let{orderGoodsList:r=[]}=e||{},n=null==t?void 0:t.includes("BBC=on"),i=null==r?void 0:r.flat(1/0),o=null==i?void 0:i.filter((e=>"0"==e.pack_detail_status||!!e.pack_detail_status));return n&&(null==o?void 0:o.length)}orderDetailB2B2CCond(e={},t=""){let{orderGoodsList:r=[]}=e||{},n=null==t?void 0:t.includes("BBC=on"),i=null==r?void 0:r.flat(1/0),o=null==i?void 0:i.filter((e=>[1,2,3].includes(+e.pack_detail_status)));return n&&(null==o?void 0:o.length)}orderDetailCancelItemCond(e={},t=""){var r;let{orderStatus:n="",subOrderStatus:i=[]}=e||{},o=[4,6].includes(+n),s=null==t?void 0:t.includes("BBC=on"),l=[];null==i||i.forEach((e=>l=[...l,...e.goodsList]));let d=null===(r=l)||void 0===r?void 0:r.flat(1/0),_=null==d?void 0:d.filter((e=>e.isCodOrderCanPartCancel||e.isCommonOrderCanPartRefund)),a=null==_?void 0:_.filter((e=>[1,2,3].includes(+e.pack_detail_status))),u=!(null==_||!_.length)&&(null==a?void 0:a.length)==(null==_?void 0:_.length);return o&&s&&u}orderDetailEditAddressCond(e={},t=""){let{orderStatus:r="",order_package_info_list:n=[],orderGoodsList:i=[]}=e||{},o=[4,6].includes(+r),s=null==t?void 0:t.includes("Shop=on"),l=null==i?void 0:i.flat(1/0),d=null==l?void 0:l.filter((e=>49==e.status)),_=null==l?void 0:l.filter((e=>[13,23,49].includes(+e.status))),a=null==n?void 0:n.map((e=>e.operationProcess)),u=!1;return null!=a&&a.length&&(a.every((e=>2===Number(e)))&&null!=d&&d.length||a.every((e=>3===Number(e)))&&null!=_&&_.length)&&(u=!0),o&&s&&u}unShipCanDelivery(e={},t="",r=[]){let{order_package_info_list:n=[]}=e||{},i=null==r?void 0:r.map((e=>{var t,r;return null===(t=e.goodsList)||void 0===t||null===(r=t[0])||void 0===r?void 0:r.reference_number})),o=null==n?void 0:n.filter((e=>null==i?void 0:i.includes((null==e?void 0:e.reference_number)||(null==e?void 0:e.packageNo)))),s=null==o?void 0:o.filter((e=>[1].includes(+e.operationProcess)));return!t||t&&!(null!=s&&s.length)}shipCanDelivery(e={},t="",r=[]){let{order_package_info_list:n=[],orderGoodsList:i=[],exist_merge_suggestion:o=""}=e||{},s=null==i?void 0:i.flat(1/0),l=null==t?void 0:t.includes("Shop=off"),d=null==t?void 0:t.includes("Shop=on"),_=null==r?void 0:r.map((e=>{var t,r;return null===(t=e.goodsList)||void 0===t||null===(r=t[0])||void 0===r?void 0:r.reference_number})),a=null==n?void 0:n.filter((e=>null==_?void 0:_.includes((null==e?void 0:e.reference_number)||(null==e?void 0:e.packageNo)))),u=null==a?void 0:a.filter((e=>[2].includes(+e.operationProcess))),c=null==a?void 0:a.some((e=>!(null!=e&&e.operationProcess))),p=null==s?void 0:s.filter((e=>49==e.status)),E=null==u?void 0:u.filter((e=>49==e.status)),g=d&&c&&(!o||0==o)&&!(null!=p&&p.length),m=d&&(null==u?void 0:u.length)&&!(null!=E&&E.length);return l||g||m}hasDiffParamsValue(e,t){let r=!1;return e={...e,country_id:e.countryId||"",tax_number:e.taxNumber||"",middle_name:e.middleName||"",english_name:e.englishName||""},["fname","lname","stateId","cityId","districtId","postcode","tel","street","address1","address2","nationalId","country_id","tax_number","english_name","middle_name","birthday","id_expire_date"].forEach((n=>{let i=e[n]||"",o=t[n]||"";(0!=Number(i)?i:Number(i))!=(0!=Number(o)?o:Number(o))&&(r=!0)})),r}sortOrderList(e=[]){var t,r,n,i;return[...(null===(r=e=null===(t=e)||void 0===t?void 0:t.flat(1/0))||void 0===r?void 0:r.filter((e=>e.is_xtra_goods)))||[],...(null===(n=e)||void 0===n?void 0:n.filter((e=>e.is_prime_goods)))||[],...(null===(i=e)||void 0===i?void 0:i.filter((e=>!(e.is_prime_goods||e.is_xtra_goods))))||[]]}getOrderStatusTextByOrderList(e=[],t={}){if(null==e||!e.length)return null;let{shipping_country_id:r,business_model:n,order_or_package_status:i,sales_area:o,toc_perform_party:s,stock_mode:l,order_package_info_list:d=[],orderGoodsList:_=[]}=t,a=null==_?void 0:_.flat(1/0);return null==e?void 0:e.find((e=>{let{orderStatus:t,saleMode:_,shoppingCountryIds:u=[],wrapStatus:c,combineType:p,combineAddress:E,salesArea:g,tocPerformParty:m,stockMode:h}=e,C=i==t,S=2==_||n==_,f=(null==u?void 0:u.includes(r))||(null==u?void 0:u.includes(+r)),v=this.getMatchWrapStatus(c,d),I=null==p||!p.length||a.some((e=>p.includes(e.combine_type))),N=null==E||!E.length||a.some((e=>E.includes(e.combine_address))),b=1!==n||null==g||!g.length||g.includes(o),P=1!==n||null==m||!m.length||m.includes(s),y=1!==n||null==h||!h.length||h.includes(l);return C&&S&&f&&v&&I&&N&&b&&P&&y}))}getMatchWrapStatus(e,t){return null==e||!(null==t||!t.length)&&(1===e?t.some((e=>{const t=(null==e?void 0:e.wrapStatus)||(null==e?void 0:e.wrap_status);return 1===t||2===t})):0===e&&t.some((e=>{const t=(null==e?void 0:e.wrapStatus)||(null==e?void 0:e.wrap_status);return 1!==t&&2!==t})))}getOrderStatusTextInfo({order:e={},language:t={},abt:r="",orderStatusTextList:n=[]}){const{order_detail_status:i,orderStatus:o}=e;if(this.orderListGetCccTextCond(e,r)){let r=this.getOrderStatusTextByOrderList(n,{...e,order_or_package_status:i});return(null==r?void 0:r.statusText)||this.orderStatus(t)[o]}return this.orderStatus(t)[o]}showGoodsQuickShipTag(e={},t={},r=""){let{is_multi_mall:n}=e||{},{status:i,quick_ship:o,is_prime_goods:s}=t||{};if(1==n)return!1;if(null!=r&&r.includes("conceal_QuickShipping"))return!1;return![75,76,77,91,5,7,20,126,127,220].includes(+i)&&!s&&(1==o||2==o)}showPackageQuickShipTag(e={},t=[],r=""){let{is_multi_mall:n}=e||{};if(1==n)return!1;if(null!=r&&r.includes("conceal_QuickShipping"))return!1;let i=t.flat(1/0),o=[75,76,77,91,5,7,20,126,127,220],s=null==i?void 0:i.filter((e=>!(null!=o&&o.includes(+e.status)||e.is_prime_goods))),l=null==s?void 0:s.filter((e=>1==e.quick_ship||2==e.quick_ship));return(null==l?void 0:l.length)&&(null==s?void 0:s.length)==(null==l?void 0:l.length)}getQuickShipTransportTimeType(e=[]){let t=null==e?void 0:e.flat(1/0),r=[75,76,77,91,5,7,20,126,127,220],n=null==t?void 0:t.filter((e=>!(null!=r&&r.includes(+e.status)||e.is_prime_goods))),i=null==n?void 0:n.filter((e=>1==e.quick_ship)),o=null==n?void 0:n.filter((e=>2==e.quick_ship));return null!=i&&i.length||null!=o&&o.length?(null==n?void 0:n.length)==(null==i?void 0:i.length)?2:1:0}giftShowOpeBtn({order:e,abt:t}){var r;return!(null!==(r=e.card_order_billno)&&void 0!==r&&r.startsWith("GC"))&&(-1==[2,8,9,10,11,14].indexOf(e.status)||[6,7].includes(e.status))&&(t.includes("email=on")||t.includes("resend=on"))}isOrderReview({order:e}){return(1==e.isCanComment||3==e.isCanComment)&&!("free trial order"==e.remark_user||1==e.marketing_type)}getStoreAddressExtraData({order:e}){var t,r,n;const{mall_list:i=[],orderGoodsList:o=[],addTime:s}=e||{},l=i.slice(0,1),d=l.map((({mall_code:e,transport_type:t})=>({mallCode:e,transportType:t}))),a=o.flat(1/0).reduce(((e,t)=>{const{product:r,quantity:n}=t||{};return e+Number((null==r?void 0:r.weight)||0)*Number(n||1)}),0),{transport_time_type:u,transport_time:c}=(null==l?void 0:l[0])||{},p=0===u?s+Number(c):null!==(t=`${c}`)&&void 0!==t&&t.includes("-")?null===(r=`${c}`)||void 0===r||null===(n=r.split("-"))||void 0===n?void 0:n[1]:c;return{mallList:d,goodsTotalWeight:a,shippingDate:p?_()(1e3*Number(p)).subtract(3,"day").unix():""}}isApolloCountryPayment({payment:e,receiver_country:t}){let r=!1;return"[object Object]"==Object.prototype.toString.call(E)&&(r=E.hasOwnProperty(t)&&E[t].includes(e)),u.log(r),r}uniqueFunc(e,t){const r=new Map;return e.filter((e=>!r.has(e[t])&&r.set(e[t],1)))}setToRobotPage({order:e={},pageName:t}){const r=e.mall_list||[],n=this.uniqueFunc(r,"store_code");let i=[],o=[];n.forEach((e=>{i.push(e.store_code),o.push(e.business_model)})),gbCommonInfo.robotStoreData={pageName:t,storeCode:i.join(),business_model:o.join()}}getRefundSpecialReason(){return{usa:136,eur:130,central:130}[p]||""}isDiffShippToEdit(e,t){let r=!1;return e={...e,country_id:e.countryId||"",tax_number:e.taxNumber||"",middle_name:e.middleName||"",english_name:e.englishName||""},["fname","lname","stateId","cityId","districtId","postcode","tel","street","address1","address2","nationalId","country_id","tax_number","english_name","middle_name","birthday","id_expire_date","city","district"].forEach((n=>{let i=e[n]||"",o=t[n]||"";(0!=Number(i)?i:Number(i))!=(0!=Number(o)?o:Number(o))&&(r=!0)})),r}checkShipEdit({order:e,isInfoFrontAbt:t,isCanRefundAbt:r,processOrStockPackage:n}){return this.unShipCanDelivery(e,t,n)&&this.shipCanDelivery(e,r,n)}async checkEditShippingOrder({orders:e=[],isInfoFrontAbt:t,isShippedAddressEnable:r,isCanRefundAbt:n,page:i=""}){if(null!=e&&e.length&&r){var o,s;let r,l,d=[],_=[];return e.forEach((e=>{let{orderStatus:o,subOrderStatus:s=[],billno:l,orderGoodsList:a=[]}=e||{},u="robot"==i?a:s,c="robot"==i?"package_state":"packageState",p=[10].includes(+o),E=null==u?void 0:u.filter((e=>["processing","out_of_stock"].includes(e[c]))),g=null==u?void 0:u.filter((e=>["shipped"].includes(e[c])));if(!p||null!=E&&E.length)null!=E&&E.length&&(r=2,null!=g&&g.length&&d.push({billno:l,check_type:1}),this.checkShipEdit({order:e,isInfoFrontAbt:t,isCanRefundAbt:n,processOrStockPackage:E})&&_.push({billno:l,display_flag:1}));else{let e=null==a?void 0:a.flat(1/0),t=null==e?void 0:e.filter((e=>"52"!=e.status));null!=t&&t.length&&d.push({billno:l,check_type:1}),r=1}})),d.length&&(l=await(0,a.W)({check_urge_request_list:d})),l=_.concat((null===(o=l)||void 0===o||null===(s=o.info)||void 0===s?void 0:s.check_urge_response_list)||[]),{urgeResList:l,type:r}}return{urgeResList:[],type:0}}checkCodRiskOrder({order:e={},language:t={},codRiskOrderAbt:r}){let{is_cod_risk:n,cod_risk_type:i}=e||{};return{showCodRiskOrder:r&&n&&!!i,showCodRiskOrderText:{repeat_before_not_all_ship:t.SHEIN_KEY_PC_26744||"",repeat_before_have_all_ship:t.SHEIN_KEY_PC_26745||"",user_rejects:t.SHEIN_KEY_PC_26746||"",repeat_duplicate_product:t.SHEIN_KEY_PC_26747||"",repeat_as_historical_rejection:t.SHEIN_KEY_PC_26748||"",repeat_as_delivering:t.SHEIN_KEY_PC_26748||""}[i]||""}}getRefundAccountTips({receiverCountry:e="",language:t={},paymentType:r="",hasAccount:n=!1}){return"Brazil"==e?!n||n&&["PayPal","bankaccount"].includes(r)?t.SHEIN_KEY_PC_27066:t.SHEIN_KEY_PC_27067:t.SHEIN_KEY_PC_20248}isFreeGiftItem(e={}){const{promotion_list:t=[],totalPrice:r}=e,n=null==t?void 0:t.some((e=>2===(null==e?void 0:e.promotion_logo_type))),i=0===Number(null==r?void 0:r.amount);return n&&i}isShowRepurchaseBtn(e={}){let t=[];return e&&Array.isArray(e.preOrderGoodsList)?t=e.preOrderGoodsList:e&&Array.isArray(e.orderGoodsList)&&(t=e.orderGoodsList),!t.every((e=>1==e.customization_flag))}getBuyTpInfo(e={},t=[]){var r,n;let{order_type:i,xtra_order_scene:o}=e||{},s=(null==t||null===(r=t.filter((e=>e.is_xtra_goods)))||void 0===r?void 0:r.length)>0,l=(null==t||null===(n=t.filter((e=>e.is_prime_goods)))||void 0===n?void 0:n.length)>0;return s&&l?5:l?5==i?1:2:s?1==o?3:4:6}isShowApplicationStatus(e={},t){let{order_type:r="",orderStatus:n=""}=e||{},i=1==r,o=[7,5,11,14,17,15].includes(+n);return t&&i&&o}getShipInfo(e={}){var t,r,n,i,o,s,l;return{fname:e.shipping_firstname,lname:e.shipping_lastname,fatherName:e.shipping_father_name,englishName:null===(t=e.subsidiary)||void 0===t?void 0:t.english_name,middleName:null===(r=e.subsidiary)||void 0===r?void 0:r.middle_name,countryId:e.shipping_country_id,country_name:e.shipping_country_name,state:e.shipping_province,city:e.shipping_city,district:e.shipping_district,street:e.shipping_street,address1:e.shipping_address_1,address2:e.shipping_address_2,postcode:e.shipping_postcode,taxNumber:e.tax_number,nationalId:e.nationalId,tel:e.shipping_telephone,new_tel:e.shipping_telephone,standbyTel:e.backupShippingTelephone,birthday:e.birthday,isDefault:0,type:e.type,memberId:e.member_id,stateId:e.shipping_state_id,cityId:e.shipping_city_id,districtId:e.shipping_district_id,storeType:null===(n=e.subsidiary)||void 0===n?void 0:n.store_type,storeId:null===(i=e.subsidiary)||void 0===i?void 0:i.store_id,storeName:e.subsidiary.store_name,passportNumber:null===(o=e.subsidiary)||void 0===o?void 0:o.passport_number,passportIssuePlace:null===(s=e.subsidiary)||void 0===s?void 0:s.issue_place,passportIssueDate:null===(l=e.subsidiary)||void 0===l?void 0:l.issue_date,lat:e.shipping_lat,lng:e.shipping_lng,calendar_type:e.calendar_type,id_expire_date:e.id_expire_date}}}},396548:function(e,t,r){r.d(t,{W:function(){return i}});var n=r(843326);function i({check_urge_request_list:e}={}){return null!=e&&e.length?(0,n.Z)({method:"POST",url:"/api/orders/query/queryBatchCheckUrge",data:{check_urge_request_list:e}}):{}}}}]);