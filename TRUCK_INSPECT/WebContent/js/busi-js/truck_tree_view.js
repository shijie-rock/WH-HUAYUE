//对bootstrap treeview的操作
// 选中父节点时，选中所有子节点
    function getChildNodeIdArr(node) {   
        var ts = [];  
        if (node.nodes) {    
            for (x in node.nodes) {   
            	console.log('node.nodes[]:='+x);
                ts.push(node.nodes[x].nodeId);     
                if (node.nodes[x].nodes) {      
                    var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);      
                    for (j in getNodeDieDai) {       
                            ts.push(getNodeDieDai[j]);     
                    }     
                }    
            }   
        } else {    
            ts.push(node.nodeId);   
        }   
        return ts;  
    }
    
 // 选中所有子节点时，选中父节点 取消子节点时取消父节点
    function setParentNodeCheck(node) {   
        var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);   
        if (parentNode.nodes) {    
            var checkedCount = 0;    
            for (x in parentNode.nodes) {     
                if (parentNode.nodes[x].state.checked) {      
                    checkedCount ++;     
                } else {      
                    break;     
                }    
            }    
            if (checkedCount == parentNode.nodes.length) {  //如果子节点全部被选 父全选
                $("#treeview-checkable").treeview("checkNode", parentNode.nodeId);
                setParentNodeCheck(parentNode);    
            }else {   //如果子节点未全部被选 父未全选
                $('#treeview-checkable').treeview('uncheckNode', parentNode.nodeId); 
                setParentNodeCheck(parentNode);        
            }   
        }  
    }
    
 // 取消父节点时 取消所有子节点
    function setChildNodeUncheck(node) { 
        if (node.nodes) {   
            var ts = [];    //当前节点子集中未被选中的集合 
            for (x in node.nodes) { 
                if (!node.nodes[x].state.checked) {  
                    ts.push(node.nodes[x].nodeId);  
                } 
                if (node.nodes[x].nodes) {      
                    var getNodeDieDai = node.nodes[x];
                    console.log(getNodeDieDai);      
                    for (j in getNodeDieDai) {
                        //if (!getNodeDieDai.nodes[x].state.checked) {        
                            ts.push(getNodeDieDai[j]); 
                        //}    
                           console.log('getNodeDieDai['+j+']:='+getNodeDieDai[j])
                    }     
                }    
            }   
        }
        return ts;  
    }
    
    function truck_onNodeChecked(event, node){ //选中节点 
        var selectNodes = getChildNodeIdArr(node); //获取所有子节点      
        if (selectNodes) { //子节点不为空，则选中所有子节点       
            $('#treeview-checkable').treeview('checkNode', [selectNodes, { silent: true }]);     
        }     
        var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);      
        setParentNodeCheck(node);  
    }
    
    /*
    function truck_onNodeUnchecked(event, node){//取消选中节点  
        // 取消父节点 子节点取消
        var selectNodes = setChildNodeUncheck(node); //获取未被选中的子节点 
        var childNodes = getChildNodeIdArr(node);    //获取所有子节点 
        if (selectNodes && selectNodes.length==0) { //有子节点且未被选中的子节点数目为0，则取消选中所有子节点   
          console.log("反选");   
          $('#treeview-checkable').treeview('uncheckNode', [childNodes, { silent: true }]);      
        }  
        // 取消节点 父节点取消
        var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);  //获取父节点
        var selectNodes = getChildNodeIdArr(node);     
        setParentNodeCheck(node); 
    }
    
    function truck_onNodeChecked(event, node){ //选中节点 
        var selectNodes = getChildNodeIdArr(node); //获取所有子节点      
        if (selectNodes) { //子节点不为空，则选中所有子节点       
            $('#treeview-checkable').treeview('checkNode', [selectNodes, { silent: true }]);     
        }     
        var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);      
        setParentNodeCheck(node);  
    }
    */
    
    function truck_onNodeUnchecked(event, node){//取消选中节点  
        // 取消父节点 子节点取消
          console.log("反选");   

    }
    
    function truck_onNodeChecked(event, node){ //选中节点 
    	//返回给定节点的节点数组
    	console.log("选中");   
    	var childNodes=$('#treeview-checkable').treeview('getNodes', node);
    	console.log("childNodes:"+childNodes);
    }

