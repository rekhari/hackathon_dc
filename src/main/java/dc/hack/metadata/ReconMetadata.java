package dc.hack.metadata;

import java.util.Set;

public class ReconMetadata {

    private String reconSource;
    private String reconTarget;
    private Set<String> keys;
    private Set<String> attsToMatch;

    public String getReconSource() {
        return reconSource;
    }

    public void setReconSource(String reconSource) {
        this.reconSource = reconSource;
    }

    public String getReconTarget() {
        return reconTarget;
    }

    public void setReconTarget(String reconTarget) {
        this.reconTarget = reconTarget;
    }

    public Set<String> getKeys() {
        return keys;
    }

    public void setKeys(Set<String> keys) {
        this.keys = keys;
    }

    public Set<String> getAttsToMatch() {
        return attsToMatch;
    }

    public void setAttsToMatch(Set<String> attsToMatch) {
        this.attsToMatch = attsToMatch;
    }
}
