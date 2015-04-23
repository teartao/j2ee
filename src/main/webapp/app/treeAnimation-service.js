define(['diamond'], function (diamond) {
    diamond.lazy.service('treeAnimationService', function ($http, $log) {
        return {
            init: function (json) {
                //end
                //init Spacetree
                //Create a new ST instance
                var st = new $jit.ST({
                    //id of viz container element
                    injectInto: 'infovis',
                    //set duration for the animation
                    duration: 800,
                    //set animation transition type
                    transition: $jit.Trans.Quart.easeInOut,
                    //set distance between node and its children
                    levelDistance: 50,
                    //enable panning
                    Navigation: {
                        enable: true,
                        panning: true
                    },
                    //set node and edge styles
                    //set overridable=true for styling individual
                    //nodes or edges
                    Node: {
                        height: 20,
                        width: 60,
                        type: 'rectangle',
                        color: '#aaa',
                        overridable: true
                    },

                    Edge: {
                        type: 'bezier',
                        overridable: true
                    },

                    //This method is called on DOM label creation.
                    //Use this method to add event handlers and styles to
                    //your node.
                    onCreateLabel: function (label, node) {
                        label.id = node.id;
                        label.innerHTML = node.name;
                        label.onclick = function () {
                            if (normal.checked) {
                                st.onClick(node.id);
                            } else {
                                st.setRoot(node.id, 'animate');
                            }
                        };
                        //set label styles
                        var style = label.style;
                        style.width = 60 + 'px';
                        style.height = 17 + 'px';
                        style.cursor = 'pointer';
                        style.color = '#333';
                        style.fontSize = '0.8em';
                        style.textAlign = 'center';
                        style.paddingTop = '3px';
                    },

                    //This method is called right before plotting
                    //a node. It's useful for changing an individual node
                    //style properties before plotting it.
                    //The data properties prefixed with a dollar
                    //sign will override the global node style properties.
                    onBeforePlotNode: function (node) {
                        //add some color to the nodes in the path between the
                        //root node and the selected node.
                        if (node.selected) {
                            node.data.$color = "#ff7";
                        }
                        else {
                            delete node.data.$color;
                            //if the node belongs to the last plotted level
                            if (!node.anySubnode("exist")) {
                                //count children number
                                var count = 0;
                                node.eachSubnode(function (n) {
                                    count++;
                                });
                                //assign a node color based on
                                //how many children it has
                                node.data.$color = ['#aaa', '#baa', '#caa', '#daa', '#eaa', '#faa'][count];
                            }
                        }
                    },

                    //This method is called right before plotting
                    //an edge. It's useful for changing an individual edge
                    //style properties before plotting it.
                    //Edge data proprties prefixed with a dollar sign will
                    //override the Edge global style properties.
                    onBeforePlotLine: function (adj) {
                        if (adj.nodeFrom.selected && adj.nodeTo.selected) {
                            adj.data.$color = "#eed";
                            adj.data.$lineWidth = 3;
                        }
                        else {
                            delete adj.data.$color;
                            delete adj.data.$lineWidth;
                        }
                    }
                });
                //load json data
                st.loadJSON(json);
                //compute node positions and layout
                st.compute();
                //optional: make a translation of the tree
                st.geom.translate(new $jit.Complex(-200, 0), "current");
                //emulate a click on the root node.
                st.onClick(st.root);
                //end
                //Add event handlers to switch spacetree orientation.
                var top = $jit.id('r-top'),
                    left = $jit.id('r-left'),
                    bottom = $jit.id('r-bottom'),
                    right = $jit.id('r-right'),
                    normal = $jit.id('s-normal');


                function changeHandler() {
                    if (this.checked) {
                        top.disabled = bottom.disabled = right.disabled = left.disabled = true;
                        st.switchPosition(this.value, "animate", {
                            onComplete: function () {
                                top.disabled = bottom.disabled = right.disabled = left.disabled = false;
                            }
                        });
                    }
                };

                top.onchange = left.onchange = bottom.onchange = right.onchange = changeHandler;
                //end

            }
        };
    });

});