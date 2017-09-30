// jQuery Alert Dialogs Plugin
//
// Version 1.1
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
// 14 May 2009
//
// Website: http://abeautifulsite.net/blog/2008/12/jquery-alert-dialogs/
//
// Usage:
//		jAlert( message, [title, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, title, callback] )
// 
// History:
//
//		1.00 - Released (29 December 2008)
//
//		1.01 - Fixed bug where unbinding would destroy all resize events
//
// License:
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2008 A Beautiful Site, LLC. 
//
(function($) {
	
	$.alerts = {
		
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: 0.5,                // transparency level of overlay
		overlayColor: '#333',               // base color of overlay
		draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '确定',         // text for the OK button
		cancelButton: '取消', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods
		
		alert: function(message, title, msgType, callback) {
			if( title == null ) title = 'Alert';
			$.alerts._show(title, message, msgType, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, msgType, callback) {
			if( title == null ) title = 'Confirm';
			$.alerts._show(title, message, msgType, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, title, callback) {
			if( title == null ) title = 'Prompt';
			if(callback.param){
				$.alerts._show(title, message, value, 'prompt',callback);
			}else{
				$.alerts._show(title, message, value, 'prompt', function(result) {
					if(callback.callback) {
						callback.callback(result);
					}else{
						callback(result);
					}
				});
			}
		},
		
		// Private methods
		
		//_show: function(title, msg, value, type, callback) {
		_show: function(title, msg, msgType, type, callback) {	
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div class="popup_container ">' +
			    '<div class="popup_head"><span class="popup_title"></span><a title="关闭" dlg="close" class="ico_close_d" href="javascript:;"></a></div>' +
			    '<div class="popup_content">' +
			      '<span class="popup_icon"></span>' +
			      '<div class="popup_message"></div>' +
			      '<div class=""></div>'+
				'</div>' +
				'<div class="popup_operate">' +
				'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $(".popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			//var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			var pos = ('undefined' == typeof(document.body.style.maxHeight) ) ? 'absolute' : 'fixed'; 
			
			$(".popup_container").css({
				position:pos ,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$(".popup_title").text(title);
			$(".popup_icon").addClass("jalert-"+msgType);
			//$(".popup_content").addClass(type);
			$(".popup_message").text(msg);
			$(".popup_message").html( $(".popup_message").text().replace(/\n/g, '<br />') );
			
			$(".popup_container").css({
				/*minWidth: $(".popup_container").outerWidth(),
				maxWidth: $(".popup_container").outerWidth()*/
				/*minWidth: 250,
				maxWidth: 250*/
			});
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			$(".ico_close_d").click( function() {
				$.alerts._hide();
				if( callback ){
					if(!callback.param){
						callback(false);
					}
				}
			});
			switch( type ) {
				case 'alert':
					$(".popup_icon").addClass("icon_info_b");
					$(".popup_operate").append('<input type="button" value="' + $.alerts.okButton + '" class="popup_ok" />');
					$(".popup_ok").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$(".popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $(".popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$(".popup_icon").addClass("icon_info_b");
					$(".popup_operate").append('<input type="button" value="' + $.alerts.okButton + '" class="confirm_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" class="confirm_cancel" />');
					$(".confirm_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$(".confirm_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$(".confirm_ok").focus();
					$(".confirm_ok,.confirm_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $(".confirm_ok").trigger('click');
						if( e.keyCode == 27 ) $(".confirm_cancel").trigger('click');
					});
				break;
				case 'prompt':
					var input="<input type=\"text\" placeholder=\"请输入\"  class=\"popup_prompt\" />";
					if(callback.param){
						var value="";
						if(callback.param.value)value=callback.param.value;
						input="<input type=\"text\" maxlength=\""+callback.param.maxlength+"\" value=\""+value+"\" placeholder=\"请输入\"  class=\"popup_prompt\" />";
					}
					$(".popup_message").append('<br />'+input+'').append('<br /><input type="button" value="' + $.alerts.okButton + '" class="prompt_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" class="prompt_cancel" />');
					//$(".popup_prompt").width( $(".popup_message").width() );
					$(".prompt_ok").click( function() {
						var val = $(".popup_prompt").val();
						if(callback.param && callback.param.validate){
							if(callback.param.validate($(".popup_prompt"))){
								$.alerts._hide();
								if( callback.param.callback ) callback.param.callback( val );
							}
						}else{
							$.alerts._hide();
							if( callback ) callback( val );
						}
					});
					$(".prompt_cancel").click( function() {
						$.alerts._hide();
						if( callback ){
							if(!callback.param){
								callback(null);
							}
						}
					});
					$(".popup_prompt, .prompt_ok, .prompt_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $(".prompt_ok").trigger('click');
						if( e.keyCode == 27 ) $(".prompt_cancel").trigger('click');
					});
					if( value ) $(".popup_prompt").val(value);
					$(".popup_prompt").focus().select();
				break;
			}
			
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$(".popup_container").draggable({ handle: $(".popup_head") });
					$(".popup_head").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$(".popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div class="popup_overlay"></div>');
					$(".popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$(".popup_overlay").remove();
				break;
			}
		},
		_reposition: function() {
			var top = (($(window).height() / 2) - ($(".popup_container").outerHeight()/2)) + $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($(".popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			// IE6 fix
			if( 'undefined' == typeof(document.body.style.maxHeight)) top = top + $(window).scrollTop();
			var scrollTop = window.parent.document.documentElement.scrollTop;
			if(scrollTop==0) 
			{
			    scrollTop = window.parent.document.body.scrollTop+200;
			}
			$(".popup_container").css({
				top: scrollTop+'px',
				left: left + 'px',
				zoom:1
			});
			$(".popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
		
	}
	
	// Shortuct functions
	jAlert = function(message, title, msgType, callback) {
		$.alerts.alert(message, title, msgType, callback);
	}
	
	jConfirm = function(message, title, msgType, callback) {
		$.alerts.confirm(message, title, msgType, callback);
	};
		
	jPrompt = function(message, value, title, callback) {
		$.alerts.prompt(message, value, title, callback);
	};
	
})(jQuery);