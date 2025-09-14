package com.project.movieapp.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList

class MultiStackNavigator<T : NavKey>(
    private val startTab: T
) {
    private val stacks: MutableMap<T, SnapshotStateList<T>> =
        mutableMapOf(startTab to mutableStateListOf(startTab))

    var activeTab by mutableStateOf(startTab)
        private set

    // Merged backstack that your NavDisplay renders
    val backStack = mutableStateListOf<T>(startTab)

    private fun rebuildBackStack() {
        backStack.clear()
        val activeStack = stacks[activeTab] ?: emptyList()
        if (activeTab == startTab) {
            backStack.addAll(activeStack)
        } else {
            val rootStack = stacks[startTab] ?: emptyList()
            backStack.addAll(rootStack + activeStack)
        }
    }

    fun switchTab(tab: T) {
        if (stacks[tab] == null) stacks[tab] = mutableStateListOf(tab)
        activeTab = tab
        rebuildBackStack()
    }

    fun push(key: T) {
        stacks[activeTab]?.add(key)
        rebuildBackStack()
    }

    fun pop() {
        val stack = stacks[activeTab] ?: return
        if (stack.size > 1) {
            stack.removeLastOrNull()
        } else if (activeTab != startTab) {
            activeTab = startTab
        }
        rebuildBackStack()
    }

    fun replace(vararg keys: T) {
        stacks[activeTab] = mutableStateListOf(*keys)
        rebuildBackStack()
    }
}
