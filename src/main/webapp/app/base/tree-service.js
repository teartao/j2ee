/**
 * Created with IntelliJ IDEA.
 * User: WangFu
 * Date: 14-3-24
 * Time: 下午4:12
 */
define(['diamond'], function (diamond) {
    diamond.lazy.service('angularTreeService', function ($log) {
        /**
         * 将ArrayList形式的数据rawData转化为嵌套形式的树结构,满足于组件 angular-tree-control
         * @author WangFu
         * @param rawData 原始数据，数组形式[{groupcode:"",parentcode: "",name:""},{groupcode:"",parentcode: "",name:""},...]
         * @param inputOption 输入数据配置选项 包括原始数据的父子关系维护参数(option.pkId, option.fkId)，根节点的值(option.rootValue)，显示在树节点上的参数名(option.name)
         * 该参数可选，<默认配置> 为 inputOption = {pkId:"groupcode", fkId: "parentcode", rootValue:"-1", label:"name"};
         * @param outputOption 输出数据配置选项 包括输出的数据里面的参数
         * 该参数可选，<默认配置> 为 outputOption = {nodeName:"name", sonNode:"children", withOtherNodeProperty: false} 如果withOtherNodeProperty设置为true, 会在输出数据中按节点
         * 层次加上原来数据的所有属性，默认只输出基本的属性
         * @returns {Array} 返回嵌套形式的数组形式为 [{{pkId}}:"",{{fkId}}:"",{{nodeName}}:"",{{sonNode}}:[  ]]  --- {{ }}记号表示取变量的值
         * 如果传入的数据格式并非预期的那样，则返回空数组
         */
        function convertToTree(rawData, inputOption, outputOption) {
            rawData = angular.copy(rawData);
            if (!Array.isArray(rawData)) {
                throw new TypeError("rawData should be an array!");
            }
            if (inputOption == undefined) {
                inputOption = {pkId: "groupcode", fkId: "parentcode", rootValue: "-1", label: "name"};
            }
            if (outputOption == undefined) {
                outputOption = {nodeName: "name", sonNode: "children", withOtherNodeProperty: false};
            }
            var pkId = inputOption.pkId;
            var fkId = inputOption.fkId;
            var rootValue = inputOption.rootValue;
            var label = inputOption.label;
            var nodeName = outputOption.nodeName;
            var sonNode = outputOption.sonNode;
            var tree = [];
            var rootNode = getRootNode(rawData);
            if (rootNode == undefined) {
                return [];
            }
            var node = {};
            if (outputOption.withOtherNodeProperty) {
                for (var property in rootNode) {
                    node[property] = rootNode[property];
                }
            }
            node[pkId] = rootNode[pkId];
            node[fkId] = rootValue;
            node[nodeName] = rootNode[label];
            node[sonNode] = getSonNodes(rawData, rootNode);
            node.isLastChild = true;
            checkLastChild(node);
            node.parentNodes = [];
            pushParentNodes(node);
            tree.push(node);
            return tree;

            function getRootNode(rawData) {
                var rootNode;
                for (var i = 0; i < rawData.length; i++) {
                    if (rawData[i][fkId] == rootValue) {
                        rootNode = rawData[i];
                    }
                }
                return rootNode;
            }

            function getSonNodes(rawData, parentNode) {
                var sonNodes = [];
                for (var i = 0; i < rawData.length; i++) {
                    if (rawData[i][fkId] == parentNode[pkId]) {
                        var node = {};
                        if (outputOption.withOtherNodeProperty) {
                            for (var property in rawData[i]) {
                                node[property] = rawData[i][property];
                            }
                        }
                        node[pkId] = rawData[i][pkId];
                        node[fkId] = parentNode[pkId];
                        node[nodeName] = rawData[i][label];
                        node[sonNode] = getSonNodes(rawData, node);
                        sonNodes.push(node);
                    }
                }
                return sonNodes;
            }

            function checkLastChild(node) {
                for (var index in node[sonNode]) {
                    node[sonNode][index].isLastChild = (index == node[sonNode].length - 1);
                    checkLastChild(node[sonNode][index]);
                }
            }

            function pushParentNodes(parentNode) {
                for (var index in parentNode[sonNode]) {
                    var node = parentNode[sonNode][index];
                    node.parentNodes = angular.copy(parentNode.parentNodes);
                    node.parentNodes.push({'pkId': parentNode[pkId], isLastChild: parentNode.isLastChild});
                    pushParentNodes(node);
                }
            }
        }

        function getTreeNode(tree, node, option) {
            setDefaultOption(option);
            var pkid = option.pkid;
            var sonNode = option.sonNode;
            for (var index in tree) {
                if (tree[index][pkid] == node[pkid]) {
                    return tree[index];
                }
            }
            for (var index in tree) {
                var nodeFound = getTreeNode(tree[index][sonNode], node, option);
                if (nodeFound) {
                    return nodeFound;
                }
            }
            return null;
        }

        function getParentNode(tree, node, option) {
            setDefaultOption(option);
            var pkid = option.pkid;
            var sonNode = option.sonNode;
            var fkid = option.fkid;
            for (var index in tree) {
                for (var sonIndex in tree[index][sonNode]) {
                    if (tree[index][sonNode][sonIndex][pkid] == node[pkid] ||
                        tree[index][pkid] == node[fkid]) {
                        return tree[index];
                    }
                }
            }
            for (var index in tree) {
                var nodeFound = getParentNode(tree[index][sonNode], node, option)
                if (nodeFound) {
                    return nodeFound
                }
            }
            return null;
        }

        function setDefaultOption(option) {
            if (!option) {
                option = {pkid: "groupcode", fkid: "parentcode", sonNode: "children"};
            }
            if (!option.pkid) {
                option.pkid = "groupcode";
            }
            if (!option.fkid) {
                option.fkid = "parentcode";
            }
            if (!option.sonNode) {
                option.sonNode = "children";
            }
            return option;
        }

        return {
            getTreeModel: convertToTree,
            getFirstLeafNode: function (treeData, sonNode) {
                var sonNode = sonNode ? sonNode : "children";
                if (treeData == null) {
                    return;
                }
                var node = {};
                if (treeData[sonNode].length == 0) {
                    node = treeData;
                } else {
                    node = this.getFirstLeafNode(treeData[sonNode][0]);
                }
                return node;
            },
            getTreeNode: getTreeNode,
            getParentNode: getParentNode,
            testConvertTree: function () {
                var responseFromServer = {"data": [
                    {
                        "name": "带钢厂",
                        "notetype": "organization",
                        "parentid": "f21a0349-34ec-11e4-8676-00505689428a",
                        "id": "6685d3b3-9502-49e8-ba9d-6994e9205f66"
                    },
                    {
                        "name": "江苏XXX有限公司",
                        "notetype": "organization",
                        "parentid": "root",
                        "id": "f21a0349-34ec-11e4-8676-00505689428a"
                    },
                    {
                        "name": "生产技术科",
                        "notetype": "organization",
                        "parentid": "6685d3b3-9502-49e8-ba9d-6994e9205f66",
                        "id": "f179ea98-4ad1-4c29-b553-ffc3db711aba"
                    },
                    {
                        "name": "质量技术部",
                        "notetype": "organization",
                        "parentid": "f21a0349-34ec-11e4-8676-00505689428a",
                        "id": "bc868219-f15c-4be7-a8cd-d24c853cbe9d"
                    },
                    {
                        "name": "B产品生产车间",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "1dca0f0e-ce0b-4f76-b606-ae269543b9c4"
                    },
                    {
                        "name": "公用系统",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "20425d02-4213-4840-9d48-c6d23cfbe08f"
                    },
                    {
                        "name": "A产品生产车间",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "ef36c799-8fb1-497e-90d4-f19eb969fac1"
                    },
                    {
                        "name": "一线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "4e0b1572-5900-4688-821c-c2cb0adb22b1"
                    },
                    {
                        "name": "二线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "6a86783f-8df3-4385-934c-06e72498498f"
                    },
                    {
                        "name": "三线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "8dd4f590-6f4c-46b9-8450-f198b2fc172f"
                    }
                ]
                    , "success": true};
                $log.debug(responseFromServer);var responseFromServer = {"data": [
                    {
                        "name": "带钢厂",
                        "notetype": "organization",
                        "parentid": "f21a0349-34ec-11e4-8676-00505689428a",
                        "id": "6685d3b3-9502-49e8-ba9d-6994e9205f66"
                    },
                    {
                        "name": "江苏XXX有限公司",
                        "notetype": "organization",
                        "parentid": "root",
                        "id": "f21a0349-34ec-11e4-8676-00505689428a"
                    },
                    {
                        "name": "生产技术科",
                        "notetype": "organization",
                        "parentid": "6685d3b3-9502-49e8-ba9d-6994e9205f66",
                        "id": "f179ea98-4ad1-4c29-b553-ffc3db711aba"
                    },
                    {
                        "name": "质量技术部",
                        "notetype": "organization",
                        "parentid": "f21a0349-34ec-11e4-8676-00505689428a",
                        "id": "bc868219-f15c-4be7-a8cd-d24c853cbe9d"
                    },
                    {
                        "name": "B产品生产车间",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "1dca0f0e-ce0b-4f76-b606-ae269543b9c4"
                    },
                    {
                        "name": "公用系统",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "20425d02-4213-4840-9d48-c6d23cfbe08f"
                    },
                    {
                        "name": "A产品生产车间",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "ef36c799-8fb1-497e-90d4-f19eb969fac1"
                    },
                    {
                        "name": "一线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "4e0b1572-5900-4688-821c-c2cb0adb22b1"
                    },
                    {
                        "name": "二线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "6a86783f-8df3-4385-934c-06e72498498f"
                    },
                    {
                        "name": "三线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "8dd4f590-6f4c-46b9-8450-f198b2fc172f"
                    }
                ]
                    , "success": true};
                $log.debug(responseFromServer);var responseFromServer = {"data": [
                    {
                        "name": "带钢厂",
                        "notetype": "organization",
                        "parentid": "f21a0349-34ec-11e4-8676-00505689428a",
                        "id": "6685d3b3-9502-49e8-ba9d-6994e9205f66"
                    },
                    {
                        "name": "江苏XXX有限公司",
                        "notetype": "organization",
                        "parentid": "root",
                        "id": "f21a0349-34ec-11e4-8676-00505689428a"
                    },
                    {
                        "name": "生产技术科",
                        "notetype": "organization",
                        "parentid": "6685d3b3-9502-49e8-ba9d-6994e9205f66",
                        "id": "f179ea98-4ad1-4c29-b553-ffc3db711aba"
                    },
                    {
                        "name": "质量技术部",
                        "notetype": "organization",
                        "parentid": "f21a0349-34ec-11e4-8676-00505689428a",
                        "id": "bc868219-f15c-4be7-a8cd-d24c853cbe9d"
                    },
                    {
                        "name": "B产品生产车间",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "1dca0f0e-ce0b-4f76-b606-ae269543b9c4"
                    },
                    {
                        "name": "公用系统",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "20425d02-4213-4840-9d48-c6d23cfbe08f"
                    },
                    {
                        "name": "A产品生产车间",
                        "notetype": "facility",
                        "parentid": "f179ea98-4ad1-4c29-b553-ffc3db711aba",
                        "id": "ef36c799-8fb1-497e-90d4-f19eb969fac1"
                    },
                    {
                        "name": "一线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "4e0b1572-5900-4688-821c-c2cb0adb22b1"
                    },
                    {
                        "name": "二线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "6a86783f-8df3-4385-934c-06e72498498f"
                    },
                    {
                        "name": "三线",
                        "notetype": "facility",
                        "parentid": "bc868219-f15c-4be7-a8cd-d24c853cbe9d",
                        "id": "8dd4f590-6f4c-46b9-8450-f198b2fc172f"
                    }
                ]
                    , "success": true};
                $log.debug(responseFromServer);
                var tree = this.getTreeModel(responseFromServer.data);
                $log.debug(tree);
            }
        }
    });
});