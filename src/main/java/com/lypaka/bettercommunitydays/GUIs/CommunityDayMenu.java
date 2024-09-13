package com.lypaka.bettercommunitydays.GUIs;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDay;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.lypaka.bettercommunitydays.ConfigGetters;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.ItemStackBuilder;
import com.lypaka.lypakautils.MiscHandlers.TimerDisplayBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.text.ITextComponent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommunityDayMenu {

    public static void openMenu (ServerPlayerEntity player) {

        int max = 9 * ConfigGetters.guiRows;
        ChestTemplate template = ChestTemplate.builder(ConfigGetters.guiRows).build();
        GooeyPage page = GooeyPage.builder().title(FancyText.getFormattedText(ConfigGetters.guiTitle)).template(template).build();

        List<Integer> usedSlots = new ArrayList<>(max);
        for (Map.Entry<String, String> entry : ConfigGetters.guiBorder.entrySet()) {

            String id = entry.getKey();
            String[] slotList = entry.getValue().split(", ");
            for (String s : slotList) {

                int slot = Integer.parseInt(s);
                usedSlots.add(slot);
                page.getTemplate().getSlot(slot)
                        .setButton(
                                GooeyButton.builder()
                                        .display(ItemStackBuilder.buildFromStringID(id)
                                                .setHoverName(FancyText.getFormattedText("")))
                                        .build()
                                );

            }

        }


        List<Integer> remainingSlots = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {

            if (!usedSlots.contains(i)) remainingSlots.add(i);

        }
        int index = 0;
        for (CommunityDay day : CommunityDayHandler.activeCommunityDays) {

            try {

                int endYear = day.getEndYear();
                int endMonth = day.getEndMonth();
                int endDay = day.getEndDay();
                int endHour = day.getEndHour();
                int endMinute = day.getEndMinute();
                int endSecond = day.getEndSecond();
                LocalDateTime end = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute, endSecond);
                String representDisplayName = day.getGUIDisplayNam();
                String representationSpecies = day.getGUIRepresentationSpecies();
                String pokemonSpecies;
                String form = null;
                if (representationSpecies.contains(" form:")) {

                    pokemonSpecies = representationSpecies.split(" form:")[0];
                    form = representationSpecies.split(" form:")[1];

                } else {

                    pokemonSpecies = representationSpecies;

                }
                Pokemon pokemon = PokemonBuilder.builder().species(pokemonSpecies).build();
                if (form != null) {

                    pokemon.setForm(form);

                }
                ItemStack itemStack = SpriteItemHelper.getPhoto(pokemon);
                itemStack.setHoverName(FancyText.getFormattedText(representDisplayName));
                List<String> list = day.getGUILore();
                ListNBT lore = new ListNBT();
                for (String s : list) {

                    lore.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(FancyText.getFormattedText(
                            s.replace("%time%", TimerDisplayBuilder.makeTimeReadable(end))
                    ))));

                }
                itemStack.getOrCreateTagElement("display").put("Lore", lore);

                int slot = remainingSlots.get(index);
                index++;
                page.getTemplate().getSlot(slot).setButton(GooeyButton.builder().display(itemStack).build());

            } catch (IndexOutOfBoundsException e) {

                break;

            }

        }

        UIManager.openUIForcefully(player, page);

    }

}
