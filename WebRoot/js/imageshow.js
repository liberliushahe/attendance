var xm;
var ym;

/* ==== onmousemove event ==== */
document.onmousemove = function(e){
	if(window.event) e=window.event;
	xm = (e.x || e.clientX);
	ym = (e.y || e.clientY);
}

/* ==== window resize ==== */
function resize() {
	if(diapo)diapo.resize();
}
onresize = resize;

/* ==== opacity ==== */
setOpacity = function(o, alpha){
	if(o.filters)o.filters.alpha.opacity = alpha * 100; else o.style.opacity = alpha;
}


////////////////////////////////////////////////////////////////////////////////////////////
/* ===== encapsulate script ==== */
diapo = {
	O : [],
	DC : 0,
	img : 0,
	txt : 0,
	N : 0,
	xm : 0,
	ym : 0,
	nx : 0,
	ny : 0,
	nw : 0,
	nh : 0,
	rs : 0,
	rsB : 0,
	zo : 0,
	tx_pos : 0,
	tx_var : 0,
	tx_target : 0,

	/////// script parameters ////////
	attraction : 2,
	acceleration : .9,
	dampening : .1,
	zoomOver : 2,
	zoomClick : 6,
	transparency : .8,
	font_size: 18,
	//////////////////////////////////

	/* ==== diapo resize ==== */
	resize : function(){
		with(this){
			nx = DC.offsetLeft;
			ny = DC.offsetTop;
			nw = DC.offsetWidth;
			nh = DC.offsetHeight;
			txt.style.fontSize = Math.round(nh / font_size) + "px";
			if(Math.abs(rs-rsB)<100) for(var i=0; i<N; i++) O[i].resize();
			rsB = rs;
		}
	},

	/* ==== create diapo ==== */
	CDiapo : function(o){
		/* ==== init variables ==== */
		this.o        = o;
		this.x_pos    = this.y_pos    = 0;
		this.x_origin = this.y_origin = 0;
		this.x_var    = this.y_var    = 0;
		this.x_target = this.y_target = 0;
		this.w_pos    = this.h_pos    = 0;
		this.w_origin = this.h_origin = 0;
		this.w_var    = this.h_var    = 0;
		this.w_target = this.h_target = 0;
		this.over     = false;
		this.click    = false;

		/* ==== create shadow ==== */
		this.spa = document.createElement("span");
		this.spa.className = "spaDC";
		diapo.DC.appendChild(this.spa);

		/* ==== create thumbnail image ==== */
		this.img = document.createElement("img");
		this.img.className = "imgDC";
		this.img.src = o.src;
		this.img.O = this;
		diapo.DC.appendChild(this.img);
		setOpacity(this.img, diapo.transparency);

		/* ==== mouse events ==== */
		this.img.onselectstart = new Function("return false;");
		this.img.ondrag = new Function("return false;");
		this.img.onmouseover = function(){
			diapo.tx_target=0;
			diapo.txt.innerHTML=this.O.o.alt;
			this.O.over=true;
			setOpacity(this,this.O.click?diapo.transparency:1);
		}
		this.img.onmouseout = function(){
			diapo.tx_target=-diapo.nw;
			this.O.over=false;
			setOpacity(this,diapo.transparency);
		}
		this.img.onclick = function() {
			if(!this.O.click){
				if(diapo.zo && diapo.zo != this) diapo.zo.onclick();
				this.O.click = true;
				this.O.x_origin = (diapo.nw - (this.O.w_origin * diapo.zoomClick)) / 2;
				this.O.y_origin = (diapo.nh - (this.O.h_origin * diapo.zoomClick)) / 2;
				diapo.zo = this;
				setOpacity(this,diapo.transparency);
			} else {
				this.O.click = false;
				this.O.over = false;
				this.O.resize();
				diapo.zo = 0;
			}
		}

		/* ==== rearrange thumbnails based on "imgsrc" images position ==== */
		this.resize = function (){
			with (this) {
				x_origin = o.offsetLeft;
				y_origin = o.offsetTop;
				w_origin = o.offsetWidth;
				h_origin = o.offsetHeight;
			}
		}

		/* ==== animation function ==== */
		this.position = function (){
			with (this) {
				/* ==== set target position ==== */
				w_target = w_origin;
				h_target = h_origin;
				if(over){
					/* ==== mouse over ==== */
					w_target = w_origin * diapo.zoomOver;
					h_target = h_origin * diapo.zoomOver;
					x_target = diapo.xm - w_pos / 2 - (diapo.xm - (x_origin + w_pos / 2)) / (diapo.attraction*(click?10:1));
					y_target = diapo.ym - h_pos / 2 - (diapo.ym - (y_origin + h_pos / 2)) / (diapo.attraction*(click?10:1));
				} else {
					/* ==== mouse out ==== */
					x_target = x_origin;
					y_target = y_origin;
				}
				if(click){
					/* ==== clicked ==== */
					w_target = w_origin * diapo.zoomClick;
					h_target = h_origin * diapo.zoomClick;
				}

				/* ==== magic spring equations ==== */
				x_pos += x_var = x_var * diapo.acceleration + (x_target - x_pos) * diapo.dampening;
				y_pos += y_var = y_var * diapo.acceleration + (y_target - y_pos) * diapo.dampening;
				w_pos += w_var = w_var * (diapo.acceleration * .5) + (w_target - w_pos) * (diapo.dampening * .5);
				h_pos += h_var = h_var * (diapo.acceleration * .5) + (h_target - h_pos) * (diapo.dampening * .5);
				diapo.rs += (Math.abs(x_var) + Math.abs(y_var));

				/* ==== html animation ==== */
				with(img.style){
					left   = Math.round(x_pos) + "px";
					top    = Math.round(y_pos) + "px";
					width  = Math.round(Math.max(0, w_pos)) + "px";
					height = Math.round(Math.max(0, h_pos)) + "px";
					zIndex = Math.round(w_pos);
				}
				with(spa.style){
					left   = Math.round(x_pos + w_pos * .1) + "px";
					top    = Math.round(y_pos + h_pos * .1) + "px";
					width  = Math.round(Math.max(0, w_pos * 1.1)) + "px";
					height = Math.round(Math.max(0, h_pos * 1.1)) + "px";
					zIndex = Math.round(w_pos);
				}
			}
		}
	},

	/* ==== main loop ==== */
	run : function(){
		diapo.xm = xm - diapo.nx;
		diapo.ym = ym - diapo.ny;
		/* ==== caption anim ==== */
		diapo.tx_pos += diapo.tx_var = diapo.tx_var * .9 + (diapo.tx_target - diapo.tx_pos) * .02;
		diapo.txt.style.left = Math.round(diapo.tx_pos) + "px";
		/* ==== images anim ==== */
		for(var i in diapo.O) diapo.O[i].position();
		/* ==== loop ==== */
		setTimeout("diapo.run();", 16);
	},

	/* ==== load images ==== */
	images_load : function(){
		// ===== loop until all images are loaded =====
		var M = 0;
		for(var i=0; i<diapo.N; i++) {
			if(diapo.img[i].complete) {
				diapo.img[i].style.position = "relative";
				diapo.O[i].img.style.visibility = "visible";
				diapo.O[i].spa.style.visibility = "visible";
				M++;
			}
			resize();
		}
		if(M<diapo.N) setTimeout("diapo.images_load();", 128);
	},

	/* ==== init script ==== */
	init : function() {
		diapo.DC = document.getElementById("diapoContainer");
		diapo.img = diapo.DC.getElementsByTagName("img");
		diapo.txt = document.getElementById("caption");
		diapo.N = diapo.img.length;
		for(i=0; i<diapo.N; i++) diapo.O.push(new diapo.CDiapo(diapo.img[i]));
		diapo.resize();
		diapo.tx_pos = -diapo.nw;
		diapo.tx_target = -diapo.nw;
		diapo.images_load();
		diapo.run();
	}
}

/* ==== start script ==== */
function dom_onload() {
	if(document.getElementById("diapoContainer")) diapo.init(); else setTimeout("dom_onload();", 128);
}
dom_onload();