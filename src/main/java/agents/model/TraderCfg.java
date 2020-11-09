package agents.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class TraderCfg {
    @XmlElement
    private double currentCharge;
    @XmlElement
    private double maxCharge;
    @XmlElement (name = "item")
    @XmlElementWrapper(name = "loadSchedule")
    private List<Double> loadSchedule;


}
