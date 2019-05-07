package ch.entity.sys;

import javax.persistence.*;

/**
 * Description:
 *
 * @author cy
 * @date 2019年05月07日 13:10
 * version 1.0
 */
@Table(name = "sys_serial_number")
@Entity
public class SystemSerialNumberEntity {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 模块名称
     */
    @Column(name = "module_name")
    private String moduleName;

    /**
     * 模块编码
     */
    @Column(name = "module_code")
    private String moduleCode;

    /**
     * 流水号配置模板
     */
    @Column(name = "config_templet")
    private String configTemplet;

    /**
     * 序列号最大值
     */
    @Column(name = "max_serial")
    private String maxSerial;

    /**
     * 预生成流水号数量
     */
    @Column(name = "pre_max_num")
    private String preMaxNum;

    public String getPreMaxNum() {
        return preMaxNum;
    }

    public void setPreMaxNum(String preMaxNum) {
        this.preMaxNum = preMaxNum;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getConfigTemplet() {
        return configTemplet;
    }

    public void setConfigTemplet(String configTemplet) {
        this.configTemplet = configTemplet;
    }

    public String getMaxSerial() {
        return maxSerial;
    }

    public void setMaxSerial(String maxSerial) {
        this.maxSerial = maxSerial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
