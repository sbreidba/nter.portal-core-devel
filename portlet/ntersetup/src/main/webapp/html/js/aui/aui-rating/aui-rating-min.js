AUI.add("aui-rating",function(w){var r=w.Lang,Q=r.isBoolean,ao=r.isNumber,s=r.isString,G=function(A){return(A instanceof w.NodeList)},q=function(A){return(A instanceof w.Node)},U="a",z="accessible",y="",ak="boundingBox",V="canReset",e="clearfix",K="contentBox",P="defaultSelected",O="disabled",H=".",Z="element",j="elements",X="",ab="helper",ah="hidden",B="hover",ac="href",h="javascript:;",I="id",m="input",f="inputs",ae="inputName",am="label",aq="labelNode",S="name",ad="nodeName",u="off",C="on",x="rating",o="ratingElement",b="selectedIndex",g="showTitle",J="size",W="title",T="total",ai="value",a="itemClick",ap="itemSelect",c="itemOut",aa="itemOver",l=w.ClassNameManager.getClassName,N=l(ab,e),R=l(ab,ah,z),an=l(x,am,Z),d=l(x,Z),Y=l(x,Z,B),i=l(x,Z,u),F=l(x,Z,C),D=l(x,T),n='<div class="'+an+'"></div>',v='<label><input type="radio" name="" /></label>',p="<span></span>";TPL_TOTAL_LABEL='<span class="'+R+" "+D+'"></span>';var t=w.Component.create({NAME:"rating",ATTRS:{disabled:{value:false,validator:Q},canReset:{value:true,validator:Q},defaultSelected:{value:0,validator:ao},elements:{validator:G},inputs:{validator:G},inputName:{value:y,validator:s},label:{value:y,validator:s},labelNode:{valueFn:function(){return w.Node.create(n)},validator:q},ratingElement:{valueFn:function(){var A=this;var L=w.Node.create(A.get(O)?p:v);return L.addClass(d)}},selectedIndex:{value:-1,validator:ao},showTitle:{value:true,validator:Q},size:{value:5,validator:function(A){return ao(A)&&(A>0)}},title:null,value:null},HTML_PARSER:{elements:function(A){return A.all(H+d)},inputs:function(L){var A=this;return A.get(ak).all(m)},label:function(L){var A=L.one(H+an);if(A){return A.html()}},labelNode:H+an},prototype:{initializer:function(){var A=this;A.inputElementsData={};A.after("labelChange",this._afterSetLabel)},renderUI:function(){var A=this;var L=A.get(K);L.addClass(N);A._renderLabel();A._renderElements();A._parseInputElements()},bindUI:function(){var A=this;var L=A.get(f);A._createEvents();A.on("click",A._handleClickEvent);A.on("mouseover",A._handleMouseOverEvent);A.on("mouseout",A._handleMouseOutEvent);L.on("focus",function(ar){A._handleKeyboardFocusEvent(ar,A)});L.on("key",function(ar){A._handleKeyboardSelectEvent(ar,A)},"down:13");L.on("blur",function(ar){A._handleKeyboardBlurEvent(ar,A)})},syncUI:function(){var A=this;A._syncElements();A._syncLabelUI()},clearSelection:function(){var A=this;A.get(j).each(function(L){L.removeClass(F);L.removeClass(Y)})},select:function(at){var ay=this;var au=ay.get(b);var aw=ay.get(V);if(aw&&(au==at)){at=-1}ay.set(b,at);var A=ay.get(b);var L=ay._getInputData(A);var av=(W in L)?L.title:y;var ax=(ai in L)?L.value:A;ay.fillTo(A);var ar=ay.get(f);if(A>=0&&ar.size()>0){ar.item(A).setAttribute("checked","checked")}ay.set(W,av);ay.set(ai,ax)},fillTo:function(L,ar){var A=this;A.clearSelection();if(L>=0){A.get(j).some(function(au,at){au.addClass(ar||F);return(at==Math.floor(L))})}},indexOf:function(L){var A=this;if(L.get("nodeName").toLowerCase()==m){return A.get(f).indexOf(L)}else{return A.get(j).indexOf(L)}},_canFireCustomEvent:function(L){var A=this;var ar=L.domEvent.target;return !A.get(O)&&ar.hasClass(d)},_createElements:function(){var az=this;var A=[];var au=az.get(o);var ay=az.get(f);var ax=az.get(ak).all(am);if(ay.size()){az.set(J,ay.size());ay.each(function(aF,aH){var aI;var aD=aF.get(I);if(aD){aI=ax.filter('[for="'+aF.get("id")+'"]').item(0)}if(!aI){aI=aF.ancestor(function(aL){return aL.get("nodeName").toLowerCase()=="label"})}if(aI){var aJ=aF.getAttribute(ai)!=""?aF.getAttribute(ai):aH;var aC=aF.getAttribute(S)!=""?aF.getAttribute(S):this.get(ae);var aK=ay.size();var aG=aI.text()}else{var aJ=aF.getAttribute(ai)!=""?aF.getAttribute(ai):aH;var aC=aF.getAttribute(S)!=""?aF.getAttribute(S):this.get(ae);var aK=ay.size();var aB=(aJ==0)?"rate-this-x-stars-out-of-x-singular":"rate-this-x-stars-out-of-x";var aG=Liferay.Language.get(aB,[aJ,aK])}if(aF.get("checked")){az.set(P,parseInt(aJ))}aF.remove();if(aI){aI.remove()}A.push(au.clone());var aE=A[A.length-1];aE.one(m).setAttribute(ai,aJ).setAttribute(S,aC);aE.prepend(aG)})}else{for(var aw=0,aA=this.get(J);aw<aA;aw++){A.push(au.clone());if(!az.get(O)){var av=A[A.length-1];av.one(m).setAttribute(ai,aw+1).setAttribute(S,this.get(ae));var ar=(aw==0)?"rate-this-x-stars-out-of-x-singular":"rate-this-x-stars-out-of-x";av.prepend(Liferay.Language.get(ar,[aw+1,aA]))}}if(az.get(O)){var at=Liferay.Language.get("star-rating-total",[az.get(P),az.get(J)]);var L=w.Node.create(TPL_TOTAL_LABEL);L.text(at);az.get(ak).append(L)}}return new w.NodeList(A)},_createEvents:function(){var A=this;var L=function(ar,at){A.publish(ar,{defaultFn:at,queuable:false,emitFacade:true,bubbles:true})};L(a,this._defRatingItemClickFn);L(ap,this._defRatingItemSelectFn);L(aa,this._defRatingItemOverFn);L(c,this._defRatingItemOutFn)},_defRatingItemClickFn:function(ar){var A=this;var L=ar.domEvent;A.fire(ap,{delegateEvent:ar,domEvent:L,ratingItem:L.target})},_defRatingItemSelectFn:function(L){var A=this;var ar=L.domEvent.target;A.select(A.indexOf(ar))},_defRatingItemOutFn:function(L){var A=this;A.fillTo(A.get(b))},_defRatingItemOverFn:function(ar){var A=this;var L=A.indexOf(ar.domEvent.target);A.fillTo(L,Y)},_parseInputElements:function(){var A=this;var ar=A.get(ak);var L=ar.all(m);var at=L.size();L.each(function(av,au){A.inputElementsData[au]={value:av.getAttribute(ai)||au,title:av.getAttribute(W)}})},_renderLabel:function(){var A=this;A.get(K).append(A.get(aq))},_renderElements:function(ar){var A=this;var L=A.get(K);if(!A.get(j).size()){A.set(j,A._createElements());A.set(f,A.get(j).all(m))}A.get(j).each(function(au,at){var aw=A._getInputData(at);var av=aw.content;var ax=aw.title||A.get(W)||av;if(av||ax){au.html(av||ax)}if(ax&&A.get(g)){au.setAttribute(W,ax)}if(!au.attr(ac)&&(au.get(ad).toLowerCase()==U)){au.setAttribute(ac,h)}L.appendChild(au)});A.get(f).addClass(R)},_syncElements:function(){var L=this;var A=L.get(P)-1;L.set(b,A);L.select()},_syncLabelUI:function(){var A=this;var L=A.get(am);A.get(aq).html(L)},_getInputData:function(L){var A=this;return A.inputElementsData[L]||{}},_handleClickEvent:function(L,A){A=this;if(A._canFireCustomEvent(L)){A.fire(a,{delegateEvent:L,domEvent:L.domEvent})}},_handleMouseOutEvent:function(L){var A=this;if(A._canFireCustomEvent(L)){A.fire(c,{delegateEvent:L,domEvent:L.domEvent})}},_handleMouseOverEvent:function(L){var A=this;if(A._canFireCustomEvent(L)){A.fire(aa,{delegateEvent:L,domEvent:L.domEvent})}},_handleKeyboardFocusEvent:function(L,A){if(typeof(A)=="undefined"){A=this}A.fire(aa,{delegateEvent:L,domEvent:L,ratingItem:L.target})},_handleKeyboardBlurEvent:function(L,A){if(typeof(A)=="undefined"){A=this}A.fire(c,{delegateEvent:L,domEvent:L,ratingItem:L.target})},_handleKeyboardSelectEvent:function(L,A){if(typeof(A)=="undefined"){A=this}A.fire(ap,{delegateEvent:L,domEvent:L,ratingItem:L.target})},_afterSetLabel:function(A){this._syncLabelUI()}}});var M="down",aj="thumb",al="ThumbRating",E="up",ag=l(x,aj,M),af=l(x,aj,E);var k=w.Component.create({NAME:al,ATTRS:{size:{value:2,readOnly:true}},EXTENDS:t,prototype:{renderUI:function(){var A=this;k.superclass.renderUI.apply(this,arguments);var L=A.get(j);L.addClass(i);L.item(0).addClass(af);L.item(1).addClass(ag)},fillTo:function(A,L){this.clearSelection();if(A>=0){this.get(j).item(A).addClass(L||F)}},_syncElements:function(){}}});w.Rating=t;w.StarRating=t;w.ThumbRating=k},"1.0.1",{skinnable:true,requires:["aui-base"]});