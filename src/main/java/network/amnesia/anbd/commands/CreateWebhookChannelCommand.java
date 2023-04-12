package network.amnesia.anbd.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import network.amnesia.anbd.command.Command;
import network.amnesia.anbd.command.CommandCategory;
import network.amnesia.anbd.command.ICommand;


@ICommand(name = "webhookchannel", category = CommandCategory.MODERATION, description = "Erstellt ein Webhookchannel")
public class CreateWebhookChannelCommand extends Command {
  public Outcome invoke(SlashCommandInteractionEvent event) {
    return invoke(event, null);
  }

  public Outcome invoke(SlashCommandInteractionEvent event, String name) {

    if (name == null || name == "") {
      event.reply("Du hast keinen Channelnamen angegeben");
      return Outcome.SUCCESS;
    }

    event.getGuild().createTextChannel(name).queue(textChannel -> {
      textChannel.createWebhook("Webhook").queue(webhook -> {
        event.reply(webhook.getUrl()).queue();
      });
    });

    return Outcome.SUCCESS;
  }

  @Override
  public SlashCommandData getCommandData() {
    return super.getCommandData().addOption(OptionType.STRING, "name", "Name of the new channel", false);
  }
}
