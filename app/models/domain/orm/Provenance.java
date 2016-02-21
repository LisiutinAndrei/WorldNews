package models.domain.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provenance")
public class Provenance {

    @Id
    @Column(name = PROVENANCE_ID_COLUMN)
    private long _provenanceID;
    public static final String PROVENANCE_ID_COLUMN = "prov_id";

    @Column(name = EXTERNAL_TOOL_COLUMN)
    private String _externalTool;
    public static final String EXTERNAL_TOOL_COLUMN = "tool_ex";

    @Column(name = NORMALIZATION_TOOL_COLUMN)
    private String _normalizationTool;
    public static final String NORMALIZATION_TOOL_COLUMN = "tool_norm";

    @Column(name = SOURCE_COLUMN)
    private String _source;
    public static final String SOURCE_COLUMN = "source";

    @Column(name = ELEMENT_TYPE_COLUMN)
    private String _elementType;
    public static final String ELEMENT_TYPE_COLUMN = "element_type";

    public long getProvenanceID() {
        return this._provenanceID;
    }

    public Provenance setProvenanceID(long provenanceID) {
        this._provenanceID = provenanceID;
        return this;
    }

    public String getExternalTool() {
        return this._externalTool;
    }

    public Provenance setExternalTool(String externalTool) {
        this._externalTool = externalTool;
        return this;
    }

    public String getNormalizationTool() {
        return this._normalizationTool;
    }

    public Provenance setNormalizationTool(String normalizationTool) {
        this._normalizationTool = normalizationTool;
        return this;
    }

    public String getSource() {
        return this._source;
    }

    public Provenance setSource(String source) {
        this._source = source;
        return this;
    }

    public String getElementType() {
        return this._elementType;
    }

    public Provenance setElementType(String elementType) {
        this._elementType = elementType;
        return this;
    }
}
