/*package ch.util;

import com.trm.model.func.FunctionTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionUtil {
    public static List<FunctionTree> createTreeMenus(List<FunctionTree> trees) {
        List<FunctionTree> treeMenus = null;
        if (null != trees && !trees.isEmpty()) {
            // 创建根节点
            FunctionTree root = new FunctionTree();

            // 组装Map数据
            Map<String, FunctionTree> dataMap = new HashMap<String, FunctionTree>();
            for (FunctionTree tree : trees) {
                dataMap.put(tree.getServiceCode(), tree);
            }

            // 组装树形结构
            Set<Map.Entry<String, FunctionTree>> entrySet = dataMap.entrySet();
            for (Map.Entry<String, FunctionTree> entry : entrySet) {
                FunctionTree tree = entry.getValue();
                if (null == tree.getParentFlag() || "0".equals(tree.getParentFlag())) {
                    root.getChildren().add(tree);
                } else {
                    dataMap.get(tree.getParentFlag()).getChildren().add(tree);
                }
            }
            root.sortChildren();
            treeMenus = root.getChildren();
        }
        return treeMenus;
    }
}
*/