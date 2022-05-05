package de.deniz.util;

import java.util.List;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {
	private ItemStack itemJoin;
	private ItemMeta itemMetaJoin;

	public ItemBuilder(final Material material, final short subID) {
		this.itemJoin = new ItemStack(material, 1, subID);
	}

	public ItemBuilder(final Material material) {
		this(material, (short) 0);
	}

	public ItemBuilder setName(final String name) {
		this.itemMetaJoin.setDisplayName(name);
		return this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ItemBuilder setLore(final String... lore) {
		this.itemMetaJoin.setLore((List) Arrays.asList(lore));
		return this;
	}

	public ItemBuilder setAmount(final int amount) {
		this.itemJoin.setAmount(amount);
		return this;
	}

	public ItemStack build() {
		this.itemJoin.setItemMeta(this.itemMetaJoin);
		return this.itemJoin;
	}
}