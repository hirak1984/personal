package pvt.hrk.personal.tracker.domain.model

import java.util.*

enum class SupportedUnit(vararg aliases: String) {
    NULL, ML, MG, GM, KG, OZ, CUP, TBSP, TSP, S, M, H, C, F;

    private val nameAndAliases: MutableList<String>

    companion object {
        fun lookup(nameOrAlias: String?): SupportedUnit {
            return Arrays.stream(values()).filter { unit: SupportedUnit ->
                unit.nameAndAliases.stream()
                    .anyMatch { nameAndAlias: String ->
                        nameAndAlias.equals(nameOrAlias, ignoreCase = true) }
            }
                .findFirst().orElseThrow()
        }
    }

    init {
        nameAndAliases = ArrayList()
        nameAndAliases.add(name)
        if (aliases != null) {
            nameAndAliases.addAll(Arrays.asList(*aliases))
        }
    }
}