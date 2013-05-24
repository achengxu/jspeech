	function voice(trueValue){
		var url = "./achengxutts?text="+trueValue;
		$("say").src=url;
		say.autoplay="autoplay";
	}

	function check(id){
		var check = $(id);
		if(null!=check){
			var trueData = $(id+"True");
			var trueValue = trueData.innerHTML;
			trueData.style.display="inline";
			trueData.style.color="red";
			var checkValue = check.value;
			var info = $(id+"_info");
			if(info==null){
				info = document.createElement("span");
				info.id=id+"_info";
				info.style.color="red";
				check.parentNode.appendChild(info);
			}
			if((checkValue.toLowerCase())==(trueValue.toLowerCase())){
				info.innerHTML="√";
			}else{
				info.innerHTML="×";
				voice(trueValue);
			}
			
		}
	}
	function $(id){
		return document.getElementById(id);
	}
	
	function support(){
		if (document.createElement("input").webkitSpeech === undefined) {
			var msg = "抱歉：您的浏览器不支持语言识别,推荐最新下载<a href='http://www.google.cn/intl/zh-CN/chrome/browser/'>谷歌浏览器</a> ";
			msg+="| <a href='http://www.liebao.cn/'>猎豹浏览器</a>";
			msg+="| <a href='http://http://chrome.360.cn//'>360急速浏览器</a>";
			//msg + = "推荐：谷歌浏览器</a>";
		    document.write(msg);
		}
	}
